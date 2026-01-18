package com.westminster.ewallet.consensus;

/**
 * Enumeration of the three states in the Raft consensus algorithm.  A node
 * begins as a follower, may become a candidate during an election, and
 * eventually becomes a leader upon winning an election.
 */
public enum RaftState {
    /** Follower state: passively processes requests and responds to leaders. */
    FOLLOWER,
    /** Candidate state: solicits votes from peers to become leader. */
    CANDIDATE,
    /** Leader state: actively replicates log entries and responds to clients. */
    LEADER
}