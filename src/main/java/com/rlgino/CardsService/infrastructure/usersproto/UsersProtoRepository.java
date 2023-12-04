package com.rlgino.CardsService.infrastructure.usersproto;


import com.rlgino.CardsService.domain.User;
import com.rlgino.CardsService.domain.UserRepository;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersProtoRepository implements UserRepository {
    private final UserServiceGrpc.UserServiceBlockingStub stub;

    public UsersProtoRepository(String address, int port) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(address, port).usePlaintext();
        var channel = channelBuilder.build();
        this.stub = UserServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public Optional<User> findUserByID(String id) {
        SearchRequest searchRequest = SearchRequest.newBuilder().setUser("1c4425e2-308a-4ced-82f3-dd050342c8c7").build();

        com.rlgino.CardsService.infrastructure.usersproto.User user = stub.searchUser(searchRequest);

        if (user == null) return Optional.empty();

        return Optional.of(new User());
    }
}
