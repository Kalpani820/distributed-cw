package com.westminster.ewallet.client;

import com.westminster.ewallet.util.ConsistentHash;

/**
 * PartitionResolver uses a consistent hash ring to assign accounts to
 * partitions.  Clients use this resolver to choose the correct partition
 * before sending requests.
 */
public class PartitionResolver {
    private final ConsistentHash hash;

    public PartitionResolver(int numberOfPartitions, int replicas) {
        this.hash = new ConsistentHash(numberOfPartitions, replicas);
    }

    /**
     * Determine the partition for the given account holder.  Use this when
     * creating a new account to balance load across partitions.
     */
    public int resolveForCreate(String accountHolder) {
        return hash.getPartition(accountHolder);
    }
    /**
     * Determine the partition for an existing account based on its account
     * number.  The partition ID is encoded in the account number.
     */
    public int resolveFromAccount(String accountNumber) {
        return com.westminster.ewallet.util.AccountUtils.extractPartitionId(accountNumber);
    }
}