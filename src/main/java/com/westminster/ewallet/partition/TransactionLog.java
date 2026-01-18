package com.westminster.ewallet.partition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TransactionLog tracks modifications to accounts and the last read
 * timestamp per account to enforce read-your-writes consistency.  For
 * simplicity, writes are assumed to become visible immediately once
 * recorded; a real implementation might integrate with Raft commit index.
 */
public class TransactionLog {
    private static final Logger log = LoggerFactory.getLogger(TransactionLog.class);

    // Map of account number -> list of modification timestamps
    private final Map<String, List<Long>> accountWrites;
    // Map of account number -> last read timestamp by any client
    private final Map<String, Long> lastReads;

    public TransactionLog() {
        this.accountWrites = new ConcurrentHashMap<>();
        this.lastReads = new ConcurrentHashMap<>();
    }

    /**
     * Record a write (modification) to an account at the given timestamp.
     */
    public void recordWrite(String accountNumber, long timestamp) {
        accountWrites.computeIfAbsent(accountNumber, k -> new CopyOnWriteArrayList<>())
                .add(timestamp);
        log.debug("Recorded write to {} at {}", accountNumber, timestamp);
    }

    /**
     * Record a read of an account at the given timestamp.  The last read
     * timestamp is updated to the maximum seen.
     */
    public void recordRead(String accountNumber, long timestamp) {
        lastReads.compute(accountNumber, (k, v) -> v == null ? timestamp : Math.max(v, timestamp));
    }

    /**
     * Check whether reads on the given account are permitted under
     * read-your-writes semantics.  In this simplified implementation,
     * reads are always permitted.  You could extend this to delay reads
     * until the local log is committed.
     */
    public boolean canRead(String accountNumber, long currentTimestamp) {
        // Always allow reads in this simplified model
        return true;
    }

    /**
     * Retrieve the last modification timestamp for the given account.
     */
    public long getLastModificationTime(String accountNumber) {
        List<Long> writes = accountWrites.get(accountNumber);
        if (writes == null || writes.isEmpty()) {
            return 0;
        }
        return writes.get(writes.size() - 1);
    }
}