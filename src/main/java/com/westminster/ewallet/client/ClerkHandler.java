package com.westminster.ewallet.client;

import com.westminster.ewallet.grpc.ewallet.CreateAccountRequest;
import com.westminster.ewallet.grpc.ewallet.CreateAccountResponse;
import com.westminster.ewallet.grpc.ewallet.EWalletServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * ClerkHandler encapsulates operations performed by clerks: creating new
 * accounts and querying balances (delegated to ClientHandler).  When
 * creating an account, the clerk uses a consistent hash to pick a
 * partition and sends the request to the leader of that partition.
 */
public class ClerkHandler {
    private static final Logger log = LoggerFactory.getLogger(ClerkHandler.class);

    private final PartitionResolver resolver;
    private final Map<Integer, List<String>> partitionReplicas;

    public ClerkHandler(PartitionResolver resolver, Map<Integer, List<String>> partitionReplicas) {
        this.resolver = resolver;
        this.partitionReplicas = partitionReplicas;
    }

    /**
     * Create a new account with the specified holder and initial balance.
     * Returns the account number.
     */
    public String createAccount(String holder, double initialBalance) {
        int partitionId = resolver.resolveForCreate(holder);
        String leaderAddr = findLeader(partitionId);
        if (leaderAddr == null) {
            throw new RuntimeException("No leader available for partition " + partitionId);
        }
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forTarget(leaderAddr).usePlaintext().build();
            EWalletServiceGrpc.EWalletServiceBlockingStub stub = EWalletServiceGrpc.newBlockingStub(channel);
            CreateAccountRequest req = CreateAccountRequest.newBuilder()
                    .setAccountHolder(holder)
                    .setInitialBalance(initialBalance)
                    .setTimestamp(System.currentTimeMillis())
                    .build();
            CreateAccountResponse resp = stub.createAccount(req);
            if (!resp.getSuccess()) {
                throw new RuntimeException(resp.getMessage());
            }
            return resp.getAccountNumber();
        } finally {
            if (channel != null) channel.shutdown();
        }
    }

    private String findLeader(int partitionId) {
        List<String> replicas = partitionReplicas.get(partitionId);
        if (replicas == null) return null;
        for (String addr : replicas) {
            ManagedChannel ch = null;
            try {
                ch = ManagedChannelBuilder.forTarget(addr).usePlaintext().build();
                EWalletServiceGrpc.EWalletServiceBlockingStub stub = EWalletServiceGrpc.newBlockingStub(ch);
                var req = com.westminster.ewallet.grpc.ewallet.HealthCheckRequest.newBuilder().setRequester("clerk").build();
                var resp = stub.healthCheck(req);
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