package com.rlgino.CardsService.infrastructure.usersproto;


import com.rlgino.CardsService.domain.users.User;
import com.rlgino.CardsService.domain.users.UserRepository;
import io.grpc.ManagedChannelBuilder;

import java.util.Optional;

public class UsersProtoRepository implements UserRepository {
    private final UserServiceGrpc.UserServiceBlockingStub stub;

    public UsersProtoRepository(String address, int port) {
        System.out.println("Creating grpc server");
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(address, port).usePlaintext();
        var channel = channelBuilder.build();
        this.stub = UserServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public Optional<User> findUserByID(String id) {
        SearchRequest searchRequest = SearchRequest.newBuilder().setUser(id).build();

        com.rlgino.CardsService.infrastructure.usersproto.User user = stub.searchUser(searchRequest);

        if (user == null || user.getId().isEmpty()) return Optional.empty();

        return Optional.of(new User());
    }
}
