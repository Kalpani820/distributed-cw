package com.westminster.ewallet.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ConsistentHash provides a simple consistent hashing mechanism for
 * deterministically mapping keys (e.g., account numbers) to partitions.
 * The ring is replicated {@code numberOfReplicas} times to balance load.
 */
public class ConsistentHash {
    private final SortedMap<Long, Integer> ring;
    private final int numberOfReplicas;
    private final MessageDigest md;

    /**
     * Create a consistent hash ring with the specified number of partitions and
     * virtual replicas per partition.
     *
     * @param numberOfPartitions the number of physical partitions
     * @param numberOfReplicas the number of virtual replicas per partition
     */
    public ConsistentHash(int numberOfPartitions, int numberOfReplicas) {
        this.ring = new TreeMap<>();
        this.numberOfReplicas = numberOfReplicas;
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
        // Populate ring with virtual nodes
        for (int i = 0; i < numberOfPartitions; i++) {
            addPartition(i);
        }
    }

    /**
     * Add a new partition to the hash ring.  For each replica, compute its
     * position on the ring and insert it.
     *
     * @param partitionId the physical partition identifier
     */
    private void addPartition(int partitionId) {
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(partitionId + ":" + i);
            ring.put(hash, partitionId);
        }
    }

    /**
     * Determine the partition for a given key.  The key is hashed and the
     * nearest clockwise partition on the ring is returned.
     *
     * @param key the key to map
     * @return the assigned partition
     */
    public int getPartition(String key) {
        if (ring.isEmpty()) {
            return 0;
        }
        long hash = hash(key);
        SortedMap<Long, Integer> tailMap = ring.tailMap(hash);
        Long target = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(target);
    }

    /**
     * Compute a 32-bit hash of the given key using MD5.  Only the first 4
     * bytes of the digest are used to construct the hash ring coordinate.
     */
    private long hash(String key) {
        md.reset();
        byte[] digest = md.digest(key.getBytes(StandardCharsets.UTF_8));
        long h = 0;
        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= (digest[i] & 0xFF);
        }
        return h & 0xffffffffL;
    }
}