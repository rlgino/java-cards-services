package com.rlgino.CardsService.infrastructure.usersproto;

import io.grpc.ManagedChannelBuilder;

public class ProtoClient {
    public static void main(String[] args) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext();
        var channel = channelBuilder.build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        SearchRequest searchRequest = SearchRequest.newBuilder().setUser("1c4425e2-308a-4ced-82f3-dd050342c8c7").build();

        User user = stub.searchUser(searchRequest);

        System.out.println(user.getId());
    }
}
