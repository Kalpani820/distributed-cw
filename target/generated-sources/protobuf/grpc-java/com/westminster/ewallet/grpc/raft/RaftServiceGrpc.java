package com.westminster.ewallet.grpc.raft;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Raft consensus service definition.  This service exposes the
 * operations required for leader election and log replication.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: raft.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RaftServiceGrpc {

  private RaftServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "raft.RaftService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.RequestVoteRequest,
      com.westminster.ewallet.grpc.raft.RequestVoteResponse> getRequestVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestVote",
      requestType = com.westminster.ewallet.grpc.raft.RequestVoteRequest.class,
      responseType = com.westminster.ewallet.grpc.raft.RequestVoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.RequestVoteRequest,
      com.westminster.ewallet.grpc.raft.RequestVoteResponse> getRequestVoteMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.RequestVoteRequest, com.westminster.ewallet.grpc.raft.RequestVoteResponse> getRequestVoteMethod;
    if ((getRequestVoteMethod = RaftServiceGrpc.getRequestVoteMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getRequestVoteMethod = RaftServiceGrpc.getRequestVoteMethod) == null) {
          RaftServiceGrpc.getRequestVoteMethod = getRequestVoteMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.raft.RequestVoteRequest, com.westminster.ewallet.grpc.raft.RequestVoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.raft.RequestVoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.raft.RequestVoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("RequestVote"))
              .build();
        }
      }
    }
    return getRequestVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.AppendEntriesRequest,
      com.westminster.ewallet.grpc.raft.AppendEntriesResponse> getAppendEntriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AppendEntries",
      requestType = com.westminster.ewallet.grpc.raft.AppendEntriesRequest.class,
      responseType = com.westminster.ewallet.grpc.raft.AppendEntriesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.AppendEntriesRequest,
      com.westminster.ewallet.grpc.raft.AppendEntriesResponse> getAppendEntriesMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.AppendEntriesRequest, com.westminster.ewallet.grpc.raft.AppendEntriesResponse> getAppendEntriesMethod;
    if ((getAppendEntriesMethod = RaftServiceGrpc.getAppendEntriesMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getAppendEntriesMethod = RaftServiceGrpc.getAppendEntriesMethod) == null) {
          RaftServiceGrpc.getAppendEntriesMethod = getAppendEntriesMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.raft.AppendEntriesRequest, com.westminster.ewallet.grpc.raft.AppendEntriesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AppendEntries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.raft.AppendEntriesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.raft.AppendEntriesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("AppendEntries"))
              .build();
        }
      }
    }
    return getAppendEntriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.HeartbeatRequest,
      com.westminster.ewallet.grpc.raft.HeartbeatResponse> getHeartbeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Heartbeat",
      requestType = com.westminster.ewallet.grpc.raft.HeartbeatRequest.class,
      responseType = com.westminster.ewallet.grpc.raft.HeartbeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.HeartbeatRequest,
      com.westminster.ewallet.grpc.raft.HeartbeatResponse> getHeartbeatMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.raft.HeartbeatRequest, com.westminster.ewallet.grpc.raft.HeartbeatResponse> getHeartbeatMethod;
    if ((getHeartbeatMethod = RaftServiceGrpc.getHeartbeatMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getHeartbeatMethod = RaftServiceGrpc.getHeartbeatMethod) == null) {
          RaftServiceGrpc.getHeartbeatMethod = getHeartbeatMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.raft.HeartbeatRequest, com.westminster.ewallet.grpc.raft.HeartbeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Heartbeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.raft.HeartbeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.raft.HeartbeatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("Heartbeat"))
              .build();
        }
      }
    }
    return getHeartbeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RaftServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RaftServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RaftServiceStub>() {
        @java.lang.Override
        public RaftServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RaftServiceStub(channel, callOptions);
        }
      };
    return RaftServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RaftServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RaftServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RaftServiceBlockingStub>() {
        @java.lang.Override
        public RaftServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RaftServiceBlockingStub(channel, callOptions);
        }
      };
    return RaftServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RaftServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RaftServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RaftServiceFutureStub>() {
        @java.lang.Override
        public RaftServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RaftServiceFutureStub(channel, callOptions);
        }
      };
    return RaftServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Raft consensus service definition.  This service exposes the
   * operations required for leader election and log replication.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Request a vote from peers when entering the candidate state.
     * </pre>
     */
    default void requestVote(com.westminster.ewallet.grpc.raft.RequestVoteRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.RequestVoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestVoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Append log entries to a follower's log.  Also used for heartbeats.
     * </pre>
     */
    default void appendEntries(com.westminster.ewallet.grpc.raft.AppendEntriesRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.AppendEntriesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAppendEntriesMethod(), responseObserver);
    }

    /**
     * <pre>
     * A dedicated heartbeat RPC to decouple heartbeat messages from log
     * replication.  This simplifies follower logic and keeps heartbeats
     * lightweight.
     * </pre>
     */
    default void heartbeat(com.westminster.ewallet.grpc.raft.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.HeartbeatResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHeartbeatMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RaftService.
   * <pre>
   * Raft consensus service definition.  This service exposes the
   * operations required for leader election and log replication.
   * </pre>
   */
  public static abstract class RaftServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RaftServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RaftService.
   * <pre>
   * Raft consensus service definition.  This service exposes the
   * operations required for leader election and log replication.
   * </pre>
   */
  public static final class RaftServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RaftServiceStub> {
    private RaftServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RaftServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Request a vote from peers when entering the candidate state.
     * </pre>
     */
    public void requestVote(com.westminster.ewallet.grpc.raft.RequestVoteRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.RequestVoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Append log entries to a follower's log.  Also used for heartbeats.
     * </pre>
     */
    public void appendEntries(com.westminster.ewallet.grpc.raft.AppendEntriesRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.AppendEntriesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAppendEntriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A dedicated heartbeat RPC to decouple heartbeat messages from log
     * replication.  This simplifies follower logic and keeps heartbeats
     * lightweight.
     * </pre>
     */
    public void heartbeat(com.westminster.ewallet.grpc.raft.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.HeartbeatResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RaftService.
   * <pre>
   * Raft consensus service definition.  This service exposes the
   * operations required for leader election and log replication.
   * </pre>
   */
  public static final class RaftServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RaftServiceBlockingStub> {
    private RaftServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RaftServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Request a vote from peers when entering the candidate state.
     * </pre>
     */
    public com.westminster.ewallet.grpc.raft.RequestVoteResponse requestVote(com.westminster.ewallet.grpc.raft.RequestVoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Append log entries to a follower's log.  Also used for heartbeats.
     * </pre>
     */
    public com.westminster.ewallet.grpc.raft.AppendEntriesResponse appendEntries(com.westminster.ewallet.grpc.raft.AppendEntriesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAppendEntriesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A dedicated heartbeat RPC to decouple heartbeat messages from log
     * replication.  This simplifies follower logic and keeps heartbeats
     * lightweight.
     * </pre>
     */
    public com.westminster.ewallet.grpc.raft.HeartbeatResponse heartbeat(com.westminster.ewallet.grpc.raft.HeartbeatRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHeartbeatMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RaftService.
   * <pre>
   * Raft consensus service definition.  This service exposes the
   * operations required for leader election and log replication.
   * </pre>
   */
  public static final class RaftServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RaftServiceFutureStub> {
    private RaftServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RaftServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Request a vote from peers when entering the candidate state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.raft.RequestVoteResponse> requestVote(
        com.westminster.ewallet.grpc.raft.RequestVoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Append log entries to a follower's log.  Also used for heartbeats.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.raft.AppendEntriesResponse> appendEntries(
        com.westminster.ewallet.grpc.raft.AppendEntriesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAppendEntriesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * A dedicated heartbeat RPC to decouple heartbeat messages from log
     * replication.  This simplifies follower logic and keeps heartbeats
     * lightweight.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.raft.HeartbeatResponse> heartbeat(
        com.westminster.ewallet.grpc.raft.HeartbeatRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_VOTE = 0;
  private static final int METHODID_APPEND_ENTRIES = 1;
  private static final int METHODID_HEARTBEAT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_VOTE:
          serviceImpl.requestVote((com.westminster.ewallet.grpc.raft.RequestVoteRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.RequestVoteResponse>) responseObserver);
          break;
        case METHODID_APPEND_ENTRIES:
          serviceImpl.appendEntries((com.westminster.ewallet.grpc.raft.AppendEntriesRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.AppendEntriesResponse>) responseObserver);
          break;
        case METHODID_HEARTBEAT:
          serviceImpl.heartbeat((com.westminster.ewallet.grpc.raft.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.raft.HeartbeatResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getRequestVoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.raft.RequestVoteRequest,
              com.westminster.ewallet.grpc.raft.RequestVoteResponse>(
                service, METHODID_REQUEST_VOTE)))
        .addMethod(
          getAppendEntriesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.raft.AppendEntriesRequest,
              com.westminster.ewallet.grpc.raft.AppendEntriesResponse>(
                service, METHODID_APPEND_ENTRIES)))
        .addMethod(
          getHeartbeatMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.raft.HeartbeatRequest,
              com.westminster.ewallet.grpc.raft.HeartbeatResponse>(
                service, METHODID_HEARTBEAT)))
        .build();
  }

  private static abstract class RaftServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RaftServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.westminster.ewallet.grpc.raft.Raft.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RaftService");
    }
  }

  private static final class RaftServiceFileDescriptorSupplier
      extends RaftServiceBaseDescriptorSupplier {
    RaftServiceFileDescriptorSupplier() {}
  }

  private static final class RaftServiceMethodDescriptorSupplier
      extends RaftServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RaftServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RaftServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RaftServiceFileDescriptorSupplier())
              .addMethod(getRequestVoteMethod())
              .addMethod(getAppendEntriesMethod())
              .addMethod(getHeartbeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
