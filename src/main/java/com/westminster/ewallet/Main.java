package com.westminster.ewallet;

import com.westminster.ewallet.client.ClientHandler;
import com.westminster.ewallet.client.ClerkHandler;
import com.westminster.ewallet.client.PartitionResolver;
import com.westminster.ewallet.partition.PartitionReplica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main entry point for the e-wallet distributed system.  This class can
 * bootstrap multiple partition replicas for testing and demonstrates basic
 * operations via clients and clerks.
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    /**
     * Launch the application.  With no arguments, this method starts two
     * partitions with three replicas each on localhost using default ports.
     * After servers are up, it runs a simple demonstration of account
     * creation, balance inquiry, and both within- and cross-partition
     * transfers.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            // Start sample cluster and run demo
            startClusterAndDemo();
        } else {
            // Support starting a single replica for manual deployment
            if (args.length < 5) {
                System.err.println("Usage: server <partitionId> <replicaId> <servicePort> <raftPort> <peer0,peer1,...>");
                return;
            }
            int partitionId = Integer.parseInt(args[1]);
            int replicaId = Integer.parseInt(args[2]);
            int servicePort = Integer.parseInt(args[3]);
            int raftPort = Integer.parseInt(args[4]);
            String[] peers = args[5].split(",");
            List<String> peerList = Arrays.asList(peers);
            PartitionReplica replica = new PartitionReplica(partitionId, replicaId, servicePort, raftPort, peerList);
            replica.start();
            replica.stop();
        }
    }

    private static void startClusterAndDemo() throws InterruptedException, IOException {
        // Define addresses for 2 partitions with 3 replicas each
        Map<Integer, List<String>> partitionReplicas = new HashMap<>();
        partitionReplicas.put(0, Arrays.asList(
                "localhost:5000", "localhost:5001", "localhost:5002"
        ));
        partitionReplicas.put(1, Arrays.asList(
                "localhost:5010", "localhost:5011", "localhost:5012"
        ));
        // RAFT ports for each replica
        Map<Integer, List<Integer>> raftPorts = new HashMap<>();
        raftPorts.put(0, Arrays.asList(6000, 6001, 6002));
        raftPorts.put(1, Arrays.asList(6010, 6011, 6012));
        // Start all replicas concurrently
        ExecutorService exec = Executors.newFixedThreadPool(6);
        List<PartitionReplica> replicas = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : partitionReplicas.entrySet()) {
            int partitionId = entry.getKey();
            List<String> replicaAddrs = entry.getValue();
            List<Integer> rPorts = raftPorts.get(partitionId);
            for (int i = 0; i < replicaAddrs.size(); i++) {
                int replicaId = i;
                String addr = replicaAddrs.get(i);
                int servicePort = Integer.parseInt(addr.split(":")[1]);
                int raftPort = rPorts.get(i);
                // Pass list of RAFT peer addresses
                List<String> raftPeers = new ArrayList<>();
                for (int j = 0; j < replicaAddrs.size(); j++) {
                    String peerAddr = replicaAddrs.get(j);
                    int peerRaftPort = rPorts.get(j);
                    raftPeers.add("localhost:" + peerRaftPort);
                }
                PartitionReplica replica = new PartitionReplica(partitionId, replicaId, servicePort, raftPort, raftPeers);
                replicas.add(replica);
                exec.submit(() -> {
                    try {
                        replica.start();
                        replica.blockUntilShutdown();
                    } catch (Exception e) {
                        log.error("Replica failed: {}", e.getMessage(), e);
                    }
                });
            }
        }
        // Allow cluster to elect leaders
        Thread.sleep(2000);
        // Demonstrate system functionality
        demonstrate(partitionReplicas);
        // Allow demonstration to complete then exit
        log.info("Demo complete. Press Ctrl+C to terminate.");
        Thread.currentThread().join();
    }

    private static void demonstrate(Map<Integer, List<String>> partitionReplicas) {
        PartitionResolver resolver = new PartitionResolver(2, 3);
        ClerkHandler clerk = new ClerkHandler(resolver, partitionReplicas);
        ClientHandler client = new ClientHandler(resolver, partitionReplicas);
        // Create accounts on both partitions
        String accA = clerk.createAccount("Alice", 1000);
        String accB = clerk.createAccount("Bob", 500);
        String accC = clerk.createAccount("Carol", 800);
        log.info("Created accounts: {} ({}), {} ({}), {} ({})", accA, resolver.resolveFromAccount(accA), accB, resolver.resolveFromAccount(accB), accC, resolver.resolveFromAccount(accC));
        // Within-partition transfer (if accA and accB are in same partition)
        double beforeA = client.getBalance(accA);
        double beforeB = client.getBalance(accB);
        boolean ok = client.transfer(accA, accB, 100);
        double afterA = client.getBalance(accA);
        double afterB = client.getBalance(accB);
        log.info("Within partition transfer result {}: {}->{} now {}->{}, {}->{} now {}->{}", ok, accA, accB, beforeA, afterA, beforeB, afterB);
        // Cross-partition transfer
        double beforeC = client.getBalance(accC);
        double beforeA2 = client.getBalance(accA);
        boolean crossOk = client.transfer(accC, accA, 150);
        double afterC = client.getBalance(accC);
        double afterA2 = client.getBalance(accA);
        log.info("Cross partition transfer result {}: {}->{} now {}->{}, {}->{} now {}->{}", crossOk, accC, accA, beforeC, afterC, beforeA2, afterA2);
    }
}