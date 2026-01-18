package com.westminster.ewallet.grpc.transaction;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Two-Phase Commit service for orchestrating atomic cross-partition
 * transactions.  The coordinator invokes these operations on each
 * involved participant (replica) to ensure all-or-nothing semantics.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: transaction.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TransactionServiceGrpc {

  private TransactionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "transaction.TransactionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.PrepareRequest,
      com.westminster.ewallet.grpc.transaction.PrepareResponse> getPrepareMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Prepare",
      requestType = com.westminster.ewallet.grpc.transaction.PrepareRequest.class,
      responseType = com.westminster.ewallet.grpc.transaction.PrepareResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.PrepareRequest,
      com.westminster.ewallet.grpc.transaction.PrepareResponse> getPrepareMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.PrepareRequest, com.westminster.ewallet.grpc.transaction.PrepareResponse> getPrepareMethod;
    if ((getPrepareMethod = TransactionServiceGrpc.getPrepareMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getPrepareMethod = TransactionServiceGrpc.getPrepareMethod) == null) {
          TransactionServiceGrpc.getPrepareMethod = getPrepareMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.transaction.PrepareRequest, com.westminster.ewallet.grpc.transaction.PrepareResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Prepare"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.transaction.PrepareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.transaction.PrepareResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("Prepare"))
              .build();
        }
      }
    }
    return getPrepareMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.CommitRequest,
      com.westminster.ewallet.grpc.transaction.CommitResponse> getCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Commit",
      requestType = com.westminster.ewallet.grpc.transaction.CommitRequest.class,
      responseType = com.westminster.ewallet.grpc.transaction.CommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.CommitRequest,
      com.westminster.ewallet.grpc.transaction.CommitResponse> getCommitMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.CommitRequest, com.westminster.ewallet.grpc.transaction.CommitResponse> getCommitMethod;
    if ((getCommitMethod = TransactionServiceGrpc.getCommitMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getCommitMethod = TransactionServiceGrpc.getCommitMethod) == null) {
          TransactionServiceGrpc.getCommitMethod = getCommitMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.transaction.CommitRequest, com.westminster.ewallet.grpc.transaction.CommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Commit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.transaction.CommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.transaction.CommitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("Commit"))
              .build();
        }
      }
    }
    return getCommitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.AbortRequest,
      com.westminster.ewallet.grpc.transaction.AbortResponse> getAbortMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Abort",
      requestType = com.westminster.ewallet.grpc.transaction.AbortRequest.class,
      responseType = com.westminster.ewallet.grpc.transaction.AbortResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.AbortRequest,
      com.westminster.ewallet.grpc.transaction.AbortResponse> getAbortMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.transaction.AbortRequest, com.westminster.ewallet.grpc.transaction.AbortResponse> getAbortMethod;
    if ((getAbortMethod = TransactionServiceGrpc.getAbortMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getAbortMethod = TransactionServiceGrpc.getAbortMethod) == null) {
          TransactionServiceGrpc.getAbortMethod = getAbortMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.transaction.AbortRequest, com.westminster.ewallet.grpc.transaction.AbortResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Abort"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.transaction.AbortRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.transaction.AbortResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("Abort"))
              .build();
        }
      }
    }
    return getAbortMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceStub>() {
        @java.lang.Override
        public TransactionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceStub(channel, callOptions);
        }
      };
    return TransactionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceBlockingStub>() {
        @java.lang.Override
        public TransactionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceBlockingStub(channel, callOptions);
        }
      };
    return TransactionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceFutureStub>() {
        @java.lang.Override
        public TransactionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceFutureStub(channel, callOptions);
        }
      };
    return TransactionServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Two-Phase Commit service for orchestrating atomic cross-partition
   * transactions.  The coordinator invokes these operations on each
   * involved participant (replica) to ensure all-or-nothing semantics.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * First phase: prepare.  Participants check whether they can commit the
     * transaction (e.g., sufficient funds) and lock resources.
     * </pre>
     */
    default void prepare(com.westminster.ewallet.grpc.transaction.PrepareRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.PrepareResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrepareMethod(), responseObserver);
    }

    /**
     * <pre>
     * Second phase: commit.  Participants apply the transaction.
     * </pre>
     */
    default void commit(com.westminster.ewallet.grpc.transaction.CommitRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.CommitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCommitMethod(), responseObserver);
    }

    /**
     * <pre>
     * Abort the transaction.  Participants release any locks or reserved
     * resources and roll back any tentative changes.
     * </pre>
     */
    default void abort(com.westminster.ewallet.grpc.transaction.AbortRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.AbortResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAbortMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TransactionService.
   * <pre>
   * Two-Phase Commit service for orchestrating atomic cross-partition
   * transactions.  The coordinator invokes these operations on each
   * involved participant (replica) to ensure all-or-nothing semantics.
   * </pre>
   */
  public static abstract class TransactionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TransactionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TransactionService.
   * <pre>
   * Two-Phase Commit service for orchestrating atomic cross-partition
   * transactions.  The coordinator invokes these operations on each
   * involved participant (replica) to ensure all-or-nothing semantics.
   * </pre>
   */
  public static final class TransactionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TransactionServiceStub> {
    private TransactionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * First phase: prepare.  Participants check whether they can commit the
     * transaction (e.g., sufficient funds) and lock resources.
     * </pre>
     */
    public void prepare(com.westminster.ewallet.grpc.transaction.PrepareRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.PrepareResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Second phase: commit.  Participants apply the transaction.
     * </pre>
     */
    public void commit(com.westminster.ewallet.grpc.transaction.CommitRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.CommitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Abort the transaction.  Participants release any locks or reserved
     * resources and roll back any tentative changes.
     * </pre>
     */
    public void abort(com.westminster.ewallet.grpc.transaction.AbortRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.AbortResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAbortMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TransactionService.
   * <pre>
   * Two-Phase Commit service for orchestrating atomic cross-partition
   * transactions.  The coordinator invokes these operations on each
   * involved participant (replica) to ensure all-or-nothing semantics.
   * </pre>
   */
  public static final class TransactionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TransactionServiceBlockingStub> {
    private TransactionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * First phase: prepare.  Participants check whether they can commit the
     * transaction (e.g., sufficient funds) and lock resources.
     * </pre>
     */
    public com.westminster.ewallet.grpc.transaction.PrepareResponse prepare(com.westminster.ewallet.grpc.transaction.PrepareRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrepareMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Second phase: commit.  Participants apply the transaction.
     * </pre>
     */
    public com.westminster.ewallet.grpc.transaction.CommitResponse commit(com.westminster.ewallet.grpc.transaction.CommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCommitMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Abort the transaction.  Participants release any locks or reserved
     * resources and roll back any tentative changes.
     * </pre>
     */
    public com.westminster.ewallet.grpc.transaction.AbortResponse abort(com.westminster.ewallet.grpc.transaction.AbortRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAbortMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TransactionService.
   * <pre>
   * Two-Phase Commit service for orchestrating atomic cross-partition
   * transactions.  The coordinator invokes these operations on each
   * involved participant (replica) to ensure all-or-nothing semantics.
   * </pre>
   */
  public static final class TransactionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TransactionServiceFutureStub> {
    private TransactionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * First phase: prepare.  Participants check whether they can commit the
     * transaction (e.g., sufficient funds) and lock resources.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.transaction.PrepareResponse> prepare(
        com.westminster.ewallet.grpc.transaction.PrepareRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Second phase: commit.  Participants apply the transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.transaction.CommitResponse> commit(
        com.westminster.ewallet.grpc.transaction.CommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Abort the transaction.  Participants release any locks or reserved
     * resources and roll back any tentative changes.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.transaction.AbortResponse> abort(
        com.westminster.ewallet.grpc.transaction.AbortRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAbortMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PREPARE = 0;
  private static final int METHODID_COMMIT = 1;
  private static final int METHODID_ABORT = 2;

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
        case METHODID_PREPARE:
          serviceImpl.prepare((com.westminster.ewallet.grpc.transaction.PrepareRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.PrepareResponse>) responseObserver);
          break;
        case METHODID_COMMIT:
          serviceImpl.commit((com.westminster.ewallet.grpc.transaction.CommitRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.CommitResponse>) responseObserver);
          break;
        case METHODID_ABORT:
          serviceImpl.abort((com.westminster.ewallet.grpc.transaction.AbortRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.transaction.AbortResponse>) responseObserver);
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
          getPrepareMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.transaction.PrepareRequest,
              com.westminster.ewallet.grpc.transaction.PrepareResponse>(
                service, METHODID_PREPARE)))
        .addMethod(
          getCommitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.transaction.CommitRequest,
              com.westminster.ewallet.grpc.transaction.CommitResponse>(
                service, METHODID_COMMIT)))
        .addMethod(
          getAbortMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.transaction.AbortRequest,
              com.westminster.ewallet.grpc.transaction.AbortResponse>(
                service, METHODID_ABORT)))
        .build();
  }

  private static abstract class TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.westminster.ewallet.grpc.transaction.Transaction.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransactionService");
    }
  }

  private static final class TransactionServiceFileDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier {
    TransactionServiceFileDescriptorSupplier() {}
  }

  private static final class TransactionServiceMethodDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TransactionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (TransactionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionServiceFileDescriptorSupplier())
              .addMethod(getPrepareMethod())
              .addMethod(getCommitMethod())
              .addMethod(getAbortMethod())
              .build();
        }
      }
    }
    return result;
  }
}
