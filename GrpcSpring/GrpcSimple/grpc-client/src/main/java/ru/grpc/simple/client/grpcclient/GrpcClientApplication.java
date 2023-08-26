package ru.grpc.simple.client.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.grpc.simple.proto.CommonResponse;
import ru.grpc.simple.proto.LoginRequest;
import ru.grpc.simple.proto.LogoutRequest;
import ru.grpc.simple.proto.UserGrpc;

public class GrpcClientApplication {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("0.0.0.0", 9091)
                .usePlaintext()
                .build();

        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(channel);

        LoginRequest loginRequest = LoginRequest.newBuilder()
                .setUsername("userTestLogin")
                .setPassword("qwe123")
                .build();
        CommonResponse response = stub.login(loginRequest);
        System.out.println(String.format("Login -> Response code: %d, message: %s",
                                         response.getResponseCode(),
                                         response.getResponseMessage()));
        System.out.println(String.format("Login -> All fields: %s", response.getAllFields()));

        LogoutRequest logoutRequest = LogoutRequest.newBuilder()
                .setUsername("userTestLogin")
                .build();
        response = stub.logout(logoutRequest);
        System.out.println(String.format("Logout -> Response code: %d, message: %s",
                                         response.getResponseCode(),
                                         response.getResponseMessage()));
        System.out.println(String.format("Logout -> All fields: %s", response.getAllFields()));
    }

}
