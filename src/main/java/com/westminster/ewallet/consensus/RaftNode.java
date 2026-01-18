package com.westminster.ewallet.consensus;

import com.westminster.ewallet.grpc.raft.AppendEntriesRequest;
import com.westminster.ewallet.grpc.raft.AppendEntriesResponse;
import com.westminster.ewallet.grpc.raft.HeartbeatRequest;
import com.westminster.ewallet.grpc.raft.HeartbeatResponse;
import com.westminster.ewallet.grpc.raft.RaftServiceGrpc;
import com.westminster.ewallet.grpc.raft.RequestVoteRequest;
import com.westminster.ewallet.grpc.raft.RequestVoteResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of a Raft consensus node.  Each replica runs an instance
 * of RaftNode to participate in leader election and maintain liveness via
 * heartbeats.  This implementation is simplified and does not replicate
 * client commands; its purpose is to provide a correct leader election
 * mechanism for high availability of partitions.
 */
public class RaftNode extends RaftServiceGrpc.RaftServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(RaftNode.class);

    // Identity
    private final int nodeId;
    private final int partitionId;
    private final int port;
    private final List<String> peerAddresses;

    // Raft state
    private volatile RaftState state;
    private final AtomicInteger currentTerm;
    private volatile Integer votedFor;
    private volatile Integer leaderId;

    // Log (unused but kept for completeness)
    private final List<LogEntry> logEntries;
    private volatile int commitIndex;
    private volatile int lastApplied;

    // Timers
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> electionTimer;
    private ScheduledFuture<?> heartbeatTask;
    private final Random random;

    private Server grpcServer;

    public RaftNode(int nodeId, int partitionId, int port, List<String> peerAddresses) {
        this.nodeId = nodeId;
        this.partitionId = partitionId;
        this.port = port;
        this.peerAddresses = new ArrayList<>(peerAddresses);
        this.state = RaftState.FOLLOWER;
        this.currentTerm = new AtomicInteger(0);
        this.votedFor = null;
        this.leaderId = null;
        this.logEntries = new CopyOnWriteArrayList<>();
        this.commitIndex = 0;
        this.lastApplied = 0;
        this.scheduler = Executors.newScheduledThreadPool(4);
        this.random = new Random();
    }

    /**
     * Start the gRPC server and election timer.
     */
    public void start() throws IOException {
        grpcServer = ServerBuilder.forPort(port)
                .addService(this)
                .build()
                .start();
        log.info("Raft node {} for partition {} listening on port {}", nodeId, partitionId, port);
        resetElectionTimer();
    }

    /**
     * Shut down the node and cancel timers.
     */
    public void stop() {
        if (electionTimer != null) {
            electionTimer.cancel(true);
        }
        if (heartbeatTask != null) {
            heartbeatTask.cancel(true);
        }
        scheduler.shutdownNow();
        if (grpcServer != null) {
            grpcServer.shutdown();
        }
    }

    /**
     * Check whether this node is currently the leader.
     */
    public boolean isLeader() {
        return state == RaftState.LEADER;
    }

    /**
     * Return the address of the current leader (host:port).  If unknown,
     * returns {@code null}.
     */
    public String getLeaderAddress() {
        Integer leader = leaderId;
        if (leader != null && leader < peerAddresses.size()) {
            return peerAddresses.get(leader);
        }
        return null;
    }

    // ----------------------------------------------------------------------
    // Raft RPC handlers
    // ----------------------------------------------------------------------

    @Override
    public void requestVote(RequestVoteRequest request,
                            StreamObserver<RequestVoteResponse> responseObserver) {
        int term = request.getTerm();
        int candidateId = request.getCandidateId();
        int lastLogIndex = request.getLastLogIndex();
        int lastLogTerm = request.getLastLogTerm();

        boolean voteGranted = false;
        synchronized (this) {
            // Step down if necessary
            if (term > currentTerm.get()) {
                currentTerm.set(term);
                state = RaftState.FOLLOWER;
                votedFor = null;
            }
            // Grant vote if candidate's term matches and we haven't voted
            if (term == currentTerm.get() && (votedFor == null || votedFor == candidateId)) {
                // Compare logs (we have no log, so accept if candidate's log is at least as up to date)
                int lastIndex = logEntries.isEmpty() ? 0 : logEntries.get(logEntries.size() - 1).getIndex();
                int lastTerm = logEntries.isEmpty() ? 0 : logEntries.get(logEntries.size() - 1).getTerm();
                boolean upToDate = (lastLogTerm > lastTerm) ||
                        (lastLogTerm == lastTerm && lastLogIndex >= lastIndex);
                if (upToDate) {
                    voteGranted = true;
                    votedFor = candidateId;
                    resetElectionTimer();
                    log.debug("Node {} votes for candidate {} in term {}", nodeId, candidateId, term);
                }
            }
        }
        RequestVoteResponse response = RequestVoteResponse.newBuilder()
                .setTerm(currentTerm.get())
                .setVoteGranted(voteGranted)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void appendEntries(AppendEntriesRequest request,
                              StreamObserver<AppendEntriesResponse> responseObserver) {
        int term = request.getTerm();
        int leader = request.getLeaderId();
        boolean success = false;
        synchronized (this) {
            if (term >= currentTerm.get()) {
                currentTerm.set(term);
                state = RaftState.FOLLOWER;
                leaderId = leader;
                resetElectionTimer();
                success = true;
            }
        }
        AppendEntriesResponse response = AppendEntriesResponse.newBuilder()
                .setTerm(currentTerm.get())
                .setSuccess(success)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void heartbeat(HeartbeatRequest request,
                          StreamObserver<HeartbeatResponse> responseObserver) {
        int term = request.getTerm();
        int leader = request.getLeaderId();
        synchronized (this) {
            if (term >= currentTerm.get()) {
                currentTerm.set(term);
                state = RaftState.FOLLOWER;
                leaderId = leader;
                resetElectionTimer();
            }
        }
        HeartbeatResponse response = HeartbeatResponse.newBuilder()
                .setTerm(currentTerm.get())
                .setAcknowledged(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // ----------------------------------------------------------------------
    // Election and heartbeat logic
    // ----------------------------------------------------------------------

    /**
     * Reset the election timer.  When the timer expires, the node starts an
     * election if it is not already leader.
     */
    private void resetElectionTimer() {
        if (electionTimer != null) {
            electionTimer.cancel(false);
        }
        int timeout = 150 + random.nextInt(150); // 150-300ms
        electionTimer = scheduler.schedule(this::startElection, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * Start an election by becoming a candidate and requesting votes from
     * peers.  If a majority of votes is obtained, become leader.
     */
    private void startElection() {
        synchronized (this) {
            state = RaftState.CANDIDATE;
            currentTerm.incrementAndGet();
            votedFor = nodeId;
            leaderId = null;
            log.info("Node {} starting election for term {}", nodeId, currentTerm.get());
        }
        final AtomicInteger votes = new AtomicInteger(1); // vote for self
        final int required = (peerAddresses.size() + 1) / 2 + 1;
        // Request votes asynchronously
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < peerAddresses.size(); i++) {
            if (i == nodeId) continue;
            final int peerIndex = i;
            executor.submit(() -> requestVoteFromPeer(peerIndex, votes, required));
        }
        executor.shutdown();
        resetElectionTimer();
    }

    private void requestVoteFromPeer(int peerIndex, AtomicInteger votes, int required) {
        String target = peerAddresses.get(peerIndex);
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
            RaftServiceGrpc.RaftServiceBlockingStub stub = RaftServiceGrpc.newBlockingStub(channel);
            int lastIndex = logEntries.isEmpty() ? 0 : logEntries.get(logEntries.size() - 1).getIndex();
            int lastTerm = logEntries.isEmpty() ? 0 : logEntries.get(logEntries.size() - 1).getTerm();
            RequestVoteRequest req = RequestVoteRequest.newBuilder()
                    .setTerm(currentTerm.get())
                    .setCandidateId(nodeId)
                    .setLastLogIndex(lastIndex)
                    .setLastLogTerm(lastTerm)
                    .build();
            RequestVoteResponse resp = stub.requestVote(req);
            if (resp.getVoteGranted()) {
                int total = votes.incrementAndGet();
                log.debug("Node {} received vote from {} (total {} / {})", nodeId, peerIndex, total, required);
                if (total >= required && state == RaftState.CANDIDATE) {
                    becomeLeader();
                }
            } else if (resp.getTerm() > currentTerm.get()) {
                // Step down if peer has newer term
                synchronized (this) {
                    currentTerm.set(resp.getTerm());
                    state = RaftState.FOLLOWER;
                    votedFor = null;
                }
            }
        } catch (Exception e) {
            log.debug("Node {} failed to request vote from {}: {}", nodeId, peerIndex, e.getMessage());
        } finally {
            if (channel != null) {
                channel.shutdown();
            }
        }
    }

    private void becomeLeader() {
        synchronized (this) {
            if (state != RaftState.CANDIDATE) {
                return;
            }
            state = RaftState.LEADER;
            leaderId = nodeId;
            log.info("Node {} became leader for partition {} in term {}", nodeId, partitionId, currentTerm.get());
            // Cancel election timer
            if (electionTimer != null) {
                electionTimer.cancel(false);
            }
            // Start sending heartbeats
            startHeartbeats();
        }
    }

    private void startHeartbeats() {
        heartbeatTask = scheduler.scheduleAtFixedRate(() -> {
            if (state == RaftState.LEADER) {
                sendHeartbeats();
            }
        }, 0, 75, TimeUnit.MILLISECONDS);
    }

    private void sendHeartbeats() {
        for (int i = 0; i < peerAddresses.size(); i++) {
            if (i == nodeId) continue;
            final int peerIndex = i;
            scheduler.execute(() -> {
                String target = peerAddresses.get(peerIndex);
                ManagedChannel channel = null;
                try {
                    channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
                    RaftServiceGrpc.RaftServiceBlockingStub stub = RaftServiceGrpc.newBlockingStub(channel);
                    HeartbeatRequest req = HeartbeatRequest.newBuilder()
                            .setTerm(currentTerm.get())
                            .setLeaderId(nodeId)
                            .build();
                    stub.heartbeat(req);
                } catch (Exception e) {
                    log.trace("Heartbeat to {} failed: {}", peerIndex, e.getMessage());
                } finally {
                    if (channel != null) {
                        channel.shutdown();
                    }
                }
            });
        }
    }
}