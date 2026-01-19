package com.westminster.ewallet.transaction;

import com.westminster.ewallet.grpc.*;
import com.westminster.ewallet.grpc.EWalletServiceGrpc;
import com.westminster.ewallet.grpc.HealthCheckRequest;
import com.westminster.ewallet.grpc.HealthCheckResponse;
import com.westminster.ewallet.grpc.transaction.*;
import com.westminster.ewallet.util.AccountUtils;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TransactionCoordinator orchestrates cross-partition fund transfers using
 * the two-phase commit protocol.  It consults the partition resolver to
 * determine the partitions involved, locates the leaders of those
 * partitions, and then executes prepare/commit or abort on each.
 */
public class TransactionCoordinator {
    private static final Logger log = LoggerFactory.getLogger(TransactionCoordinator.class);

    private final Map<Integer, List<String>> partitionReplicas;

    public TransactionCoordinator(Map<Integer, List<String>> partitionReplicas) {
        this.partitionReplicas = new ConcurrentHashMap<>(partitionReplicas);
    }

    /**
     * Execute a cross-partition transfer.  Returns true if successful.
     *
     * @param fromAccount source account
     * @param toAccount target account
     * @param amount amount to transfer
     */
    public boolean transfer(String fromAccount, String toAccount, double amount) {
        int fromPartition = AccountUtils.extractPartitionId(fromAccount);
        int toPartition = AccountUtils.extractPartitionId(toAccount);
        // If both accounts are in the same partition, coordinator should not be used
        if (fromPartition == toPartition) {
            log.error("Cross-partition coordinator called for within-partition transfer");
            return false;
        }
        String txnId = AccountUtils.generateTransactionId();
        long ts = System.currentTimeMillis();
        Participant fromParticipant = findLeader(fromPartition);
        Participant toParticipant = findLeader(toPartition);
        if (fromParticipant == null || toParticipant == null) {
            log.error("Unable to locate leaders for partitions {} and {}", fromPartition, toPartition);
            return false;
        }
        log.info("Initiating 2PC: txn {} from {} to {} amount {}", txnId, fromAccount, toAccount, amount);
        // Phase 1: prepare
        boolean fromOk = prepare(fromParticipant, txnId, fromAccount, toAccount, amount, ts);
        boolean toOk = prepare(toParticipant, txnId, fromAccount, toAccount, amount, ts);
        if (fromOk && toOk) {
            // Phase 2: commit
            boolean fromCommit = commit(fromParticipant, txnId);
            boolean toCommit = commitCredit(toParticipant, txnId, fromAccount, toAccount, amount, ts);
            return fromCommit && toCommit;
        } else {
            // Abort both
            abort(fromParticipant, txnId);
            abort(toParticipant, txnId);
            return false;
        }
    }

    private boolean prepare(Participant p, String txnId, String from, String to, double amount, long ts) {
        ManagedChannel ch = null;
        try {
            ch = ManagedChannelBuilder.forTarget(p.address).usePlaintext().build();
            TransactionServiceGrpc.TransactionServiceBlockingStub stub = TransactionServiceGrpc.newBlockingStub(ch);
            PrepareRequest req = PrepareRequest.newBuilder()
                    .setTransactionId(txnId)
                    .setFromAccount(from)
                    .setToAccount(to)
                    .setAmount(amount)
                    .setTimestamp(ts)
                    .build();
            PrepareResponse resp = stub.prepare(req);
            return resp.getVoteCommit();
        } catch (Exception e) {
            log.warn("Prepare phase failed for participant {}: {}", p.address, e.getMessage());
            return false;
        } finally {
            if (ch != null) {
                ch.shutdown();
            }
        }
    }

    private boolean commit(Participant p, String txnId) {
        ManagedChannel ch = null;
        try {
            ch = ManagedChannelBuilder.forTarget(p.address).usePlaintext().build();
            TransactionServiceGrpc.TransactionServiceBlockingStub stub = TransactionServiceGrpc.newBlockingStub(ch);
            CommitRequest req = CommitRequest.newBuilder().setTransactionId(txnId).build();
            CommitResponse resp = stub.commit(req);
            return resp.getSuccess();
        } catch (Exception e) {
            log.warn("Commit phase failed for participant {}: {}", p.address, e.getMessage());
            return false;
        } finally {
            if (ch != null) {
                ch.shutdown();
            }
        }
    }

    // For the credit side of a cross-partition transfer, we do not need
    // prepare/reserve funds: we simply apply the credit during commit.
    private boolean commitCredit(Participant p, String txnId, String from, String to, double amount, long ts) {
        ManagedChannel ch = null;
        try {
            ch = ManagedChannelBuilder.forTarget(p.address).usePlaintext().build();
            // On the credit partition, we cheat by creating a local transfer request
            // using the EWalletService to credit the account.  We call Transfer RPC
            // with fromAccount == toAccount so that only credit happens.
            EWalletServiceGrpc.EWalletServiceBlockingStub walletStub = EWalletServiceGrpc.newBlockingStub(ch);
            TransferRequest transfer = TransferRequest.newBuilder()
                    .setFromAccount(to) // credit uses same account as source/target
                    .setToAccount(to)
                    .setAmount(amount)
                    .setTimestamp(ts)
                    .setTransactionId(txnId)
                    .build();
            walletStub.transfer(transfer);
            return true;
        } catch (Exception e) {
            log.warn("Credit commit failed for participant {}: {}", p.address, e.getMessage());
            return false;
        } finally {
            if (ch != null) ch.shutdown();
        }
    }

    private void abort(Participant p, String txnId) {
        ManagedChannel ch = null;
        try {
            ch = ManagedChannelBuilder.forTarget(p.address).usePlaintext().build();
            TransactionServiceGrpc.TransactionServiceBlockingStub stub = TransactionServiceGrpc.newBlockingStub(ch);
            AbortRequest req = AbortRequest.newBuilder().setTransactionId(txnId).build();
            stub.abort(req);
        } catch (Exception e) {
            log.warn("Abort phase failed for participant {}: {}", p.address, e.getMessage());
        } finally {
            if (ch != null) ch.shutdown();
        }
    }

    /**
     * Determine the current leader for the given partition by consulting
     * replicas.  Returns a Participant representing the leader's 2PC port.
     */
    private Participant findLeader(int partitionId) {
        List<String> replicas = partitionReplicas.get(partitionId);
        if (replicas == null) return null;
        for (int i = 0; i < replicas.size(); i++) {
            String addr = replicas.get(i);
            ManagedChannel ch = null;
            try {
                ch = ManagedChannelBuilder.forTarget(addr).usePlaintext().build();
                EWalletServiceGrpc.EWalletServiceBlockingStub stub = EWalletServiceGrpc.newBlockingStub(ch);
                HealthCheckRequest req = HealthCheckRequest.newBuilder().setRequester("coordinator").build();
                HealthCheckResponse resp = stub.healthCheck(req);
                if (resp.getIsLeader()) {
                    // The 2PC service runs on servicePort+1000; parse port
                    String[] parts = addr.split(":");
                    int basePort = Integer.parseInt(parts[1]);
                    String leaderAddr = parts[0] + ":" + (basePort + 1000);
                    return new Participant(partitionId, leaderAddr);
                }
            } catch (Exception e) {
                log.trace("Health check failed for {}: {}", addr, e.getMessage());
            } finally {
                if (ch != null) ch.shutdown();
            }
        }
        return null;
    }

    private static class Participant {
        final int partitionId;
        final String address;
        Participant(int pid, String addr) {
            this.partitionId = pid;
            this.address = addr;
        }
    }
}