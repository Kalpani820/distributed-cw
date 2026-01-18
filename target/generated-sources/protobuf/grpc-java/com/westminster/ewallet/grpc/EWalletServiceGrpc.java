package com.westminster.ewallet.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * E-Wallet service definition for account operations
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: ewallet.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EWalletServiceGrpc {

  private EWalletServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ewallet.EWalletService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.CreateAccountRequest,
      com.westminster.ewallet.grpc.CreateAccountResponse> getCreateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAccount",
      requestType = com.westminster.ewallet.grpc.CreateAccountRequest.class,
      responseType = com.westminster.ewallet.grpc.CreateAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.CreateAccountRequest,
      com.westminster.ewallet.grpc.CreateAccountResponse> getCreateAccountMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.CreateAccountRequest, com.westminster.ewallet.grpc.CreateAccountResponse> getCreateAccountMethod;
    if ((getCreateAccountMethod = EWalletServiceGrpc.getCreateAccountMethod) == null) {
      synchronized (EWalletServiceGrpc.class) {
        if ((getCreateAccountMethod = EWalletServiceGrpc.getCreateAccountMethod) == null) {
          EWalletServiceGrpc.getCreateAccountMethod = getCreateAccountMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.CreateAccountRequest, com.westminster.ewallet.grpc.CreateAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.CreateAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.CreateAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EWalletServiceMethodDescriptorSupplier("CreateAccount"))
              .build();
        }
      }
    }
    return getCreateAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.GetBalanceRequest,
      com.westminster.ewallet.grpc.GetBalanceResponse> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = com.westminster.ewallet.grpc.GetBalanceRequest.class,
      responseType = com.westminster.ewallet.grpc.GetBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.GetBalanceRequest,
      com.westminster.ewallet.grpc.GetBalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.GetBalanceRequest, com.westminster.ewallet.grpc.GetBalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = EWalletServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (EWalletServiceGrpc.class) {
        if ((getGetBalanceMethod = EWalletServiceGrpc.getGetBalanceMethod) == null) {
          EWalletServiceGrpc.getGetBalanceMethod = getGetBalanceMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.GetBalanceRequest, com.westminster.ewallet.grpc.GetBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.GetBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.GetBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EWalletServiceMethodDescriptorSupplier("GetBalance"))
              .build();
        }
      }
    }
    return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.TransferRequest,
      com.westminster.ewallet.grpc.TransferResponse> getTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Transfer",
      requestType = com.westminster.ewallet.grpc.TransferRequest.class,
      responseType = com.westminster.ewallet.grpc.TransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.TransferRequest,
      com.westminster.ewallet.grpc.TransferResponse> getTransferMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.TransferRequest, com.westminster.ewallet.grpc.TransferResponse> getTransferMethod;
    if ((getTransferMethod = EWalletServiceGrpc.getTransferMethod) == null) {
      synchronized (EWalletServiceGrpc.class) {
        if ((getTransferMethod = EWalletServiceGrpc.getTransferMethod) == null) {
          EWalletServiceGrpc.getTransferMethod = getTransferMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.TransferRequest, com.westminster.ewallet.grpc.TransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.TransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.TransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EWalletServiceMethodDescriptorSupplier("Transfer"))
              .build();
        }
      }
    }
    return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.HealthCheckRequest,
      com.westminster.ewallet.grpc.HealthCheckResponse> getHealthCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HealthCheck",
      requestType = com.westminster.ewallet.grpc.HealthCheckRequest.class,
      responseType = com.westminster.ewallet.grpc.HealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.HealthCheckRequest,
      com.westminster.ewallet.grpc.HealthCheckResponse> getHealthCheckMethod() {
    io.grpc.MethodDescriptor<com.westminster.ewallet.grpc.HealthCheckRequest, com.westminster.ewallet.grpc.HealthCheckResponse> getHealthCheckMethod;
    if ((getHealthCheckMethod = EWalletServiceGrpc.getHealthCheckMethod) == null) {
      synchronized (EWalletServiceGrpc.class) {
        if ((getHealthCheckMethod = EWalletServiceGrpc.getHealthCheckMethod) == null) {
          EWalletServiceGrpc.getHealthCheckMethod = getHealthCheckMethod =
              io.grpc.MethodDescriptor.<com.westminster.ewallet.grpc.HealthCheckRequest, com.westminster.ewallet.grpc.HealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HealthCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.HealthCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westminster.ewallet.grpc.HealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EWalletServiceMethodDescriptorSupplier("HealthCheck"))
              .build();
        }
      }
    }
    return getHealthCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EWalletServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EWalletServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EWalletServiceStub>() {
        @java.lang.Override
        public EWalletServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EWalletServiceStub(channel, callOptions);
        }
      };
    return EWalletServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EWalletServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EWalletServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EWalletServiceBlockingStub>() {
        @java.lang.Override
        public EWalletServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EWalletServiceBlockingStub(channel, callOptions);
        }
      };
    return EWalletServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EWalletServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EWalletServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EWalletServiceFutureStub>() {
        @java.lang.Override
        public EWalletServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EWalletServiceFutureStub(channel, callOptions);
        }
      };
    return EWalletServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * E-Wallet service definition for account operations
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Create a new account; returns partition assignment and account number
     * </pre>
     */
    default void createAccount(com.westminster.ewallet.grpc.CreateAccountRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.CreateAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve the balance of an existing account
     * </pre>
     */
    default void getBalance(com.westminster.ewallet.grpc.GetBalanceRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.GetBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Transfer funds between accounts; may be within a partition or across
     * </pre>
     */
    default void transfer(com.westminster.ewallet.grpc.TransferRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.TransferResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * Health check for replica discovery
     * </pre>
     */
    default void healthCheck(com.westminster.ewallet.grpc.HealthCheckRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHealthCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EWalletService.
   * <pre>
   * E-Wallet service definition for account operations
   * </pre>
   */
  public static abstract class EWalletServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EWalletServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EWalletService.
   * <pre>
   * E-Wallet service definition for account operations
   * </pre>
   */
  public static final class EWalletServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EWalletServiceStub> {
    private EWalletServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EWalletServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EWalletServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create a new account; returns partition assignment and account number
     * </pre>
     */
    public void createAccount(com.westminster.ewallet.grpc.CreateAccountRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.CreateAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve the balance of an existing account
     * </pre>
     */
    public void getBalance(com.westminster.ewallet.grpc.GetBalanceRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.GetBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Transfer funds between accounts; may be within a partition or across
     * </pre>
     */
    public void transfer(com.westminster.ewallet.grpc.TransferRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.TransferResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Health check for replica discovery
     * </pre>
     */
    public void healthCheck(com.westminster.ewallet.grpc.HealthCheckRequest request,
        io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHealthCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EWalletService.
   * <pre>
   * E-Wallet service definition for account operations
   * </pre>
   */
  public static final class EWalletServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EWalletServiceBlockingStub> {
    private EWalletServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EWalletServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EWalletServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create a new account; returns partition assignment and account number
     * </pre>
     */
    public com.westminster.ewallet.grpc.CreateAccountResponse createAccount(com.westminster.ewallet.grpc.CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve the balance of an existing account
     * </pre>
     */
    public com.westminster.ewallet.grpc.GetBalanceResponse getBalance(com.westminster.ewallet.grpc.GetBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Transfer funds between accounts; may be within a partition or across
     * </pre>
     */
    public com.westminster.ewallet.grpc.TransferResponse transfer(com.westminster.ewallet.grpc.TransferRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Health check for replica discovery
     * </pre>
     */
    public com.westminster.ewallet.grpc.HealthCheckResponse healthCheck(com.westminster.ewallet.grpc.HealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHealthCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EWalletService.
   * <pre>
   * E-Wallet service definition for account operations
   * </pre>
   */
  public static final class EWalletServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EWalletServiceFutureStub> {
    private EWalletServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EWalletServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EWalletServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create a new account; returns partition assignment and account number
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.CreateAccountResponse> createAccount(
        com.westminster.ewallet.grpc.CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve the balance of an existing account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.GetBalanceResponse> getBalance(
        com.westminster.ewallet.grpc.GetBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Transfer funds between accounts; may be within a partition or across
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.TransferResponse> transfer(
        com.westminster.ewallet.grpc.TransferRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Health check for replica discovery
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westminster.ewallet.grpc.HealthCheckResponse> healthCheck(
        com.westminster.ewallet.grpc.HealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHealthCheckMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ACCOUNT = 0;
  private static final int METHODID_GET_BALANCE = 1;
  private static final int METHODID_TRANSFER = 2;
  private static final int METHODID_HEALTH_CHECK = 3;

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
        case METHODID_CREATE_ACCOUNT:
          serviceImpl.createAccount((com.westminster.ewallet.grpc.CreateAccountRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.CreateAccountResponse>) responseObserver);
          break;
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((com.westminster.ewallet.grpc.GetBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.GetBalanceResponse>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((com.westminster.ewallet.grpc.TransferRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.TransferResponse>) responseObserver);
          break;
        case METHODID_HEALTH_CHECK:
          serviceImpl.healthCheck((com.westminster.ewallet.grpc.HealthCheckRequest) request,
              (io.grpc.stub.StreamObserver<com.westminster.ewallet.grpc.HealthCheckResponse>) responseObserver);
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
          getCreateAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.CreateAccountRequest,
              com.westminster.ewallet.grpc.CreateAccountResponse>(
                service, METHODID_CREATE_ACCOUNT)))
        .addMethod(
          getGetBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.GetBalanceRequest,
              com.westminster.ewallet.grpc.GetBalanceResponse>(
                service, METHODID_GET_BALANCE)))
        .addMethod(
          getTransferMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.TransferRequest,
              com.westminster.ewallet.grpc.TransferResponse>(
                service, METHODID_TRANSFER)))
        .addMethod(
          getHealthCheckMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.westminster.ewallet.grpc.HealthCheckRequest,
              com.westminster.ewallet.grpc.HealthCheckResponse>(
                service, METHODID_HEALTH_CHECK)))
        .build();
  }

  private static abstract class EWalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EWalletServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.westminster.ewallet.grpc.Ewallet.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EWalletService");
    }
  }

  private static final class EWalletServiceFileDescriptorSupplier
      extends EWalletServiceBaseDescriptorSupplier {
    EWalletServiceFileDescriptorSupplier() {}
  }

  private static final class EWalletServiceMethodDescriptorSupplier
      extends EWalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EWalletServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (EWalletServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EWalletServiceFileDescriptorSupplier())
              .addMethod(getCreateAccountMethod())
              .addMethod(getGetBalanceMethod())
              .addMethod(getTransferMethod())
              .addMethod(getHealthCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
