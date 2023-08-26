package ru.grpc.simple.server.grpcserver;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import ru.grpc.simple.server.grpcserver.service.UserService;

import java.io.IOException;
import java.util.Map;

public class GrpcServerApplication {

    public static void main(String[] args) throws InterruptedException, IOException {

        Server server = NettyServerBuilder.forPort(9091)
                .addService(new UserService())
                .build();

        server.start();

        System.out.println(String.format("gRPC Server started on port: %d", server.getPort()));

        server.awaitTermination();
    }
}
