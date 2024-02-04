package com.rlgino.CardsService.infrastructure.usersproto;

import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Assertions;

public class ClientTest {
    // @Test
    public void ProtoClientTest() {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 3030).usePlaintext();
        var channel = channelBuilder.build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        SearchRequest searchRequest = SearchRequest.newBuilder().setUser("95854717-f395-455e-b610-95bd85d9adb1").build();

        User user = stub.searchUser(searchRequest);

        Assertions.assertEquals(user.getId(), "95854717-f395-455e-b610-95bd85d9adb1");
    }
}
