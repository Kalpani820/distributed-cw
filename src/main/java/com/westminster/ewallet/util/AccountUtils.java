package com.westminster.ewallet.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AccountUtils contains helper methods for generating unique identifiers for
 * accounts and transactions.  Account numbers embed the partition ID to aid
 * partition resolution.
 */
public final class AccountUtils {
    private static final AtomicLong counter = new AtomicLong(1000);

    private AccountUtils() {
        // no instantiation
    }

    /**
     * Generate a unique account number for the specified partition.  The
     * account number encodes the partition ID, current time in millis, and
     * an atomic counter to prevent collisions.
     *
     * @param partitionId the partition where the account will reside
     * @return a globally unique account identifier
     */
    public static String generateAccountNumber(int partitionId) {
        long timestamp = System.currentTimeMillis();
        long count = counter.incrementAndGet();
        return String.format("P%d-%d-%d", partitionId, timestamp, count);
    }

    /**
     * Generate a globally unique transaction identifier.
     *
     * @return a transaction ID
     */
    public static String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString();
    }

    /**
     * Parse the partition ID from an account number.  The account number must
     * begin with a 'P' followed by the partition ID and a hyphen.
     *
     * @param accountNumber the account number
     * @return the partition ID
     * @throws IllegalArgumentException if the format is invalid
     */
    public static int extractPartitionId(String accountNumber) {
        if (!accountNumber.startsWith("P")) {
            throw new IllegalArgumentException("Invalid account number: " + accountNumber);
        }
        String[] parts = accountNumber.split("-");
        return Integer.parseInt(parts[0].substring(1));
    }
}