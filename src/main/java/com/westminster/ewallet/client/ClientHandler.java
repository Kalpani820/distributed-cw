package com.westminster.ewallet.client;

import com.westminster.ewallet.grpc.ewallet.*;
import com.westminster.ewallet.transaction.TransactionCoordinator;
import com.westminster.ewallet.util.AccountUtils;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * ClientHandler encapsulates operations that a typical client can perform on
 * the e-wallet system: querying balances and transferring funds.  It uses
 * the partition resolver to locate partitions and interacts with leaders
 * through gRPC.  For cross-partition transfers, it leverages the
 * TransactionCoordinator.
 */
public class ClientHandler {
    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    private final PartitionResolver resolver;
    private final Map<Integer, List<String>> partitionReplicas;
    private final TransactionCoordinator coordinator;

    public ClientHandler(PartitionResolver resolver, Map<Integer, List<String>> partitionReplicas) {
        this.resolver = resolver;
        this.partitionReplicas = partitionReplicas;
        this.coordinator = new TransactionCoordinator(partitionReplicas);
    }

    /**
     * Query the balance of the given account.  The method locates the
     * appropriate partition leader and issues a GetBalance RPC.
     */
    public double getBalance(String accountNumber) {
        int partitionId = resolver.resolveFromAccount(accountNumber);
        String leaderAddr = findLeader(partitionId);
        if (leaderAddr == null) {
            throw new RuntimeException("Leader for partition " + partitionId + " not found");
        }
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forTarget(leaderAddr).usePlaintext().build();
            EWalletServiceGrpc.EWalletServiceBlockingStub stub = EWalletServiceGrpc.newBlockingStub(channel);
            GetBalanceRequest req = GetBalanceRequest.newBuilder()
                    .setAccountNumber(accountNumber)
                    .setTimestamp(System.currentTimeMillis())
                    .build();
            GetBalanceResponse resp = stub.getBalance(req);
            if (!resp.getSuccess()) {
                throw new RuntimeException(resp.getMessage());
            }
            return resp.getBalance();
        } finally {
            if (channel != null) channel.shutdown();
        }
    }

    /**
     * Transfer funds between accounts.  If accounts reside in the same
     * partition, the request is sent directly to the leader.  Otherwise,
     * the TransactionCoordinator orchestrates a 2PC across partitions.
     */
    public boolean transfer(String fromAccount, String toAccount, double amount) {
        int fromPartition = resolver.resolveFromAccount(fromAccount);
        int toPartition = resolver.resolveFromAccount(toAccount);
        if (fromPartition == toPartition) {
            // Within partition: send directly
            String leaderAddr = findLeader(fromPartition);
            if (leaderAddr == null) {
                log.error("Leader not found for partition {}", fromPartition);
                return false;
            }
            ManagedChannel channel = null;
            try {
                channel = ManagedChannelBuilder.forTarget(leaderAddr).usePlaintext().build();
                EWalletServiceGrpc.EWalletServiceBlockingStub stub = EWalletServiceGrpc.newBlockingStub(channel);
                TransferRequest req = TransferRequest.newBuilder()
                        .setFromAccount(fromAccount)
                        .setToAccount(toAccount)
                        .setAmount(amount)
                        .setTimestamp(System.currentTimeMillis())
                        .setTransactionId(AccountUtils.generateTransactionId())
                        .build();
                TransferResponse resp = stub.transfer(req);
                return resp.getSuccess();
            } catch (Exception e) {
                log.error("Transfer failed: {}", e.getMessage());
                return false;
            } finally {
                if (channel != null) channel.shutdown();
            }
        } else {
            // Cross-partition: use coordinator
            return coordinator.transfer(fromAccount, toAccount, amount);
        }
    }

    /**
     * Helper to find the leader of a partition via health check.
     */
    private String findLeader(int partitionId) {
        List<String> replicas = partitionReplicas.get(partitionId);
        if (replicas == null) return null;
        for (String addr : replicas) {
            ManagedChannel ch = null;
            try {
                ch = ManagedChannelBuilder.forTarget(addr).usePlaintext().build();
                EWalletServiceGrpc.EWalletServiceBlockingStub stub = EWalletServiceGrpc.newBlockingStub(ch);
                HealthCheckRequest hc = HealthCheckRequest.newBuilder().setRequester("client").build();
                HealthCheckResponse resp = stub.healthCheck(hc);
                if (resp.getIsLeader()) {
                    return addr;
                }
            } catch (Exception e) {
                log.trace("Health check failed for {}: {}", addr, e.getMessage());
            } finally {
                if (ch != null) ch.shutdown();
            }
        }
        return null;
    }
}