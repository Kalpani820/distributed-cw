package com.westminster.ewallet.consensus;

/**
 * Represents a single entry in the Raft log.  Each entry stores the term
 * during which the entry was created, its index in the log, the command
 * string to be applied to the state machine, and a Lamport timestamp for
 * ordering purposes.  For simplicity, commands are encoded as strings; in
 * practice they could be serialized messages.
 */
public class LogEntry {
    private final int term;
    private final int index;
    private final String command;
    private final long timestamp;

    public LogEntry(int term, int index, String command, long timestamp) {
        this.term = term;
        this.index = index;
        this.command = command;
        this.timestamp = timestamp;
    }

    public int getTerm() {
        return term;
    }

    public int getIndex() {
        return index;
    }

    public String getCommand() {
        return command;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("LogEntry(term=%d,index=%d,cmd=%s,t=%d)", term, index, command, timestamp);
    }
}