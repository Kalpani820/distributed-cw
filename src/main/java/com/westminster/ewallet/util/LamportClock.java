package com.westminster.ewallet.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * LamportClock implements a simple logical clock used to order events in a
 * distributed system.  Each node maintains a clock that is incremented on
 * every local event and updated on receipt of a message from another node.
 *
 * When sending a message, include the current clock value.  Upon receipt
 * of a message with timestamp {@code receivedTimestamp}, call
 * {@link #update(long)} to advance the local clock before processing.
 */
public class LamportClock {
    private final AtomicLong clock;

    /**
     * Create a new LamportClock starting at zero.
     */
    public LamportClock() {
        this.clock = new AtomicLong(0);
    }

    /**
     * Increment the clock for a local event.  Use this when generating a
     * timestamp for outgoing messages or recording operations.
     *
     * @return the new clock value
     */
    public long tick() {
        return clock.incrementAndGet();
    }

    /**
     * Update the clock upon receiving a message.  The new clock value is
     * {@code max(current, receivedTimestamp) + 1}.
     *
     * @param receivedTimestamp the timestamp from the incoming message
     * @return the updated clock value
     */
    public long update(long receivedTimestamp) {
        long current = clock.get();
        long updated = Math.max(current, receivedTimestamp) + 1;
        clock.set(updated);
        return updated;
    }

    /**
     * Read the current clock value without modifying it.
     *
     * @return the current clock value
     */
    public long getTime() {
        return clock.get();
    }
}