package com.westminster.ewallet.partition;

import com.westminster.ewallet.consensus.RaftNode;
import com.westminster.ewallet.grpc.*;
import com.westminster.ewallet.grpc.EWalletServiceGrpc;
import com.westminster.ewallet.grpc.CreateAccountRequest;
import com.westminster.ewallet.grpc.CreateAccountResponse;
import com.westminster.ewallet.grpc.GetBalanceRequest;
import com.westminster.ewallet.grpc.GetBalanceResponse;
import com.westminster.ewallet.grpc.TransferRequest;
import com.westminster.ewallet.grpc.TransferResponse;
import com.westminster.ewallet.grpc.HealthCheckRequest;
import com.westminster.ewallet.grpc.HealthCheckResponse;
import com.westminster.ewallet.grpc.transaction.*;
import com.westminster.ewallet.util.AccountUtils;
import com.westminster.ewallet.util.LamportClock;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;


/**
 * PartitionReplica represents one replica in a partition.  It exposes gRPC
 * services for account operations and participates in leader election via
 * Raft.  Each replica maintains its own account store and transaction log.
 */
public class PartitionReplica extends EWalletServiceGrpc.EWalletServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(PartitionReplica.class);

    private final int partitionId;
    private final int replicaId;
    private final int servicePort;
    private final int raftPort;
    private final RaftNode raftNode;

    // Local account state: accountNumber -> balance
    private final Map<String, Double> accounts;
    // Transaction log for read-your-writes semantics
    private final TransactionLog txLog;
    // Lamport clock to order operations
    private final LamportClock clock;
    // Pending 2PC transactions: txnId -> pending state
    private final Map<String, PendingTransaction> pendingTx;
    // Ordered queue of within-partition transfers awaiting execution
    private final PriorityBlockingQueue<OrderedTransfer> transferQueue;
    // Lock to serialize transfer execution
    private final ReentrantLock transferLock;

    private Server walletServer;
    private Server txServer;

    public PartitionReplica(int partitionId, int replicaId, int servicePort, int raftPort, List<String> raftPeers) {
        this.partitionId = partitionId;
        this.replicaId = replicaId;
        this.servicePort = servicePort;
        this.raftPort = raftPort;
        this.raftNode = new RaftNode(replicaId, partitionId, raftPort, raftPeers);
        this.accounts = new ConcurrentHashMap<>();
        this.txLog = new TransactionLog();
        this.clock = new LamportClock();
        this.pendingTx = new ConcurrentHashMap<>();
        this.transferQueue = new PriorityBlockingQueue<>();
        this.transferLock = new ReentrantLock();
    }

    /**
     * Start the replica: start Raft consensus and gRPC services.
     */
    public void start() throws IOException {
        raftNode.start();
        // EWallet API server (account operations) runs on servicePort
        walletServer = ServerBuilder.forPort(servicePort)
                .addService(this)
                .build()
                .start();
        // 2PC service runs on servicePort + 1000
        txServer = ServerBuilder.forPort(servicePort + 1000)
                .addService(new TxServiceImpl())
                .build()
                .start();
        log.info("PartitionReplica P{}-R{} started on ports {} (API) and {} (2PC)",
                partitionId, replicaId, servicePort, servicePort + 1000);
        // Start processing within-partition transfers
        startTransferProcessor();
    }

    /**
     * Stop the replica: shut down Raft and gRPC servers.
     */
    public void stop() {
        raftNode.stop();
        if (walletServer != null) walletServer.shutdown();
        if (txServer != null) txServer.shutdown();
    }

    // ----------------------------------------------------------------------
    // EWallet service implementations
    // ----------------------------------------------------------------------
    @Override
    public void createAccount(CreateAccountRequest request, StreamObserver<CreateAccountResponse> responseObserver) {
        String holder = request.getAccountHolder();
        double initial = request.getInitialBalance();
        long reqTs = request.getTimestamp();
        // Update logical clock
        long ts = clock.update(reqTs);
        // Only leader can handle writes; redirect clients otherwise
        if (!raftNode.isLeader()) {
            CreateAccountResponse resp = CreateAccountResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Not the leader; please contact leader")
                    .setAccountNumber("")
                    .setPartitionId(partitionId)
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
            return;
        }
        String accountNumber = AccountUtils.generateAccountNumber(partitionId);
        accounts.put(accountNumber, initial);
        txLog.recordWrite(accountNumber, ts);
        log.info("P{}-R{} created account {} for {} with balance {}", partitionId, replicaId, accountNumber, holder, initial);
        CreateAccountResponse resp = CreateAccountResponse.newBuilder()
                .setSuccess(true)
                .setAccountNumber(accountNumber)
                .setMessage("Account created")
                .setPartitionId(partitionId)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void getBalance(GetBalanceRequest request, StreamObserver<GetBalanceResponse> responseObserver) {
        String account = request.getAccountNumber();
        long reqTs = request.getTimestamp();
        long ts = clock.update(reqTs);
        if (!accounts.containsKey(account)) {
            GetBalanceResponse resp = GetBalanceResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Account not found in partition " + partitionId)
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
            return;
        }
        // Check read-your-writes; simplified always true
        if (!txLog.canRead(account, ts)) {
            GetBalanceResponse resp = GetBalanceResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Read-your-writes consistency violation")
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
            return;
        }
        double balance = accounts.get(account);
        long lastMod = txLog.getLastModificationTime(account);
        txLog.recordRead(account, ts);
        GetBalanceResponse resp = GetBalanceResponse.newBuilder()
                .setSuccess(true)
                .setBalance(balance)
                .setMessage("Balance retrieved")
                .setLastModified(lastMod)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void transfer(TransferRequest request, StreamObserver<TransferResponse> responseObserver) {
        String fromAccount = request.getFromAccount();
        String toAccount = request.getToAccount();
        double amount = request.getAmount();
        String txnId = request.getTransactionId();
        long ts = clock.update(request.getTimestamp());
        // Determine if this is within the same partition
        boolean localFrom = accounts.containsKey(fromAccount);
        boolean localTo = accounts.containsKey(toAccount);
        if (localFrom && localTo) {
            // Queue within-partition transfer; ordering via lamport timestamp
            transferQueue.offer(new OrderedTransfer(ts, request));
            TransferResponse resp = TransferResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Transfer queued")
                    .setTransactionId(txnId)
                    .setTimestamp(ts)
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        } else {
            // Cross-partition transfers should go through coordinator
            TransferResponse resp = TransferResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Cross-partition transfer must be initiated by coordinator")
                    .setTransactionId(txnId)
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void healthCheck(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
        boolean leader = raftNode.isLeader();
        String leaderAddr = raftNode.getLeaderAddress();
        HealthCheckResponse resp = HealthCheckResponse.newBuilder()
                .setIsLeader(leader)
                .setPartitionId(partitionId)
                .setReplicaId(replicaId)
                .setLeaderAddress(leaderAddr != null ? leaderAddr : "")
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    // ----------------------------------------------------------------------
    // 2PC service implementations
    // ----------------------------------------------------------------------
    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        String txnId = request.getTransactionId();
        String fromAccount = request.getFromAccount();
        double amount = request.getAmount();
        long ts = request.getTimestamp();
        boolean vote = false;
        String msg;
        transferLock.lock();
        try {
            if (!accounts.containsKey(fromAccount)) {
                msg = "Account not found";
            } else if (accounts.get(fromAccount) < amount) {
                msg = "Insufficient balance";
            } else {
                // Reserve funds by recording pending transaction
                pendingTx.put(txnId, new PendingTransaction(fromAccount, request.getToAccount(), amount, ts));
                vote = true;
                msg = "Prepared";
            }
        } finally {
            transferLock.unlock();
        }
        PrepareResponse resp = PrepareResponse.newBuilder()
                .setVoteCommit(vote)
                .setMessage(msg)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {
        String txnId = request.getTransactionId();
        boolean success = false;
        String msg;
        transferLock.lock();
        try {
            PendingTransaction pending = pendingTx.remove(txnId);
            if (pending != null) {
                // Execute debit only; credit will happen on target partition
                double fromBalance = accounts.get(pending.fromAccount);
                accounts.put(pending.fromAccount, fromBalance - pending.amount);
                txLog.recordWrite(pending.fromAccount, pending.timestamp);
                success = true;
                msg = "Commit applied";
                log.info("P{}-R{} committed part of txn {}: debited {} {}", partitionId, replicaId, txnId, pending.amount, pending.fromAccount);
            } else {
                msg = "Transaction not prepared";
            }
        } finally {
            transferLock.unlock();
        }
        CommitResponse resp = CommitResponse.newBuilder()
                .setSuccess(success)
                .setMessage(msg)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void abort(AbortRequest request, StreamObserver<AbortResponse> responseObserver) {
        String txnId = request.getTransactionId();
        pendingTx.remove(txnId);
        AbortResponse resp = AbortResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Aborted")
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    // ----------------------------------------------------------------------
    // Internal processing of within-partition transfers
    // ----------------------------------------------------------------------
    private void startTransferProcessor() {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    OrderedTransfer ot = transferQueue.take();
                    executeTransfer(ot.request, ot.timestamp);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void executeTransfer(TransferRequest req, long timestamp) {
        String from = req.getFromAccount();
        String to = req.getToAccount();
        double amount = req.getAmount();
        transferLock.lock();
        try {
            if (!accounts.containsKey(from) || !accounts.containsKey(to)) {
                log.warn("P{}-R{} transfer failed: account not found", partitionId, replicaId);
                return;
            }
            double fromBal = accounts.get(from);
            if (fromBal < amount) {
                log.warn("P{}-R{} transfer failed: insufficient balance", partitionId, replicaId);
                return;
            }
            accounts.put(from, fromBal - amount);
            accounts.put(to, accounts.get(to) + amount);
            txLog.recordWrite(from, timestamp);
            txLog.recordWrite(to, timestamp);
            log.info("P{}-R{} executed transfer {} -> {} amount {}", partitionId, replicaId, from, to, amount);
        } finally {
            transferLock.unlock();
        }
    }

    // Data structures for ordering and pending transactions
    private static class OrderedTransfer implements Comparable<OrderedTransfer> {
        final long timestamp;
        final TransferRequest request;
        OrderedTransfer(long timestamp, TransferRequest request) {
            this.timestamp = timestamp;
            this.request = request;
        }
        @Override
        public int compareTo(OrderedTransfer other) {
            return Long.compare(this.timestamp, other.timestamp);
        }
    }
    private static class PendingTransaction {
        final String fromAccount;
        final String toAccount;
        final double amount;
        final long timestamp;
        PendingTransaction(String fromAccount, String toAccount, double amount, long ts) {
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
            this.amount = amount;
            this.timestamp = ts;
        }
    }

    private class TxServiceImpl extends TransactionServiceGrpc.TransactionServiceImplBase {

    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        PartitionReplica.this.prepare(request, responseObserver);
    }

    @Override
    public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {
        PartitionReplica.this.commit(request, responseObserver);
    }

    @Override
    public void abort(AbortRequest request, StreamObserver<AbortResponse> responseObserver) {
        PartitionReplica.this.abort(request, responseObserver);
    }
}
public void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
        server.awaitTermination();
    }
}


}