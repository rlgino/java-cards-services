package com.rlgino.CardsService.infrastructure.usersproto;


import com.rlgino.CardsService.domain.users.User;
import com.rlgino.CardsService.domain.users.UserID;
import com.rlgino.CardsService.domain.users.UserRepository;
import io.grpc.*;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UsersProtoRepository implements UserRepository {
    private final UserServiceGrpc.UserServiceBlockingStub stub;

    public UsersProtoRepository(String address, int port) {
        log.info("Creating grpc client");

        ManagedChannel channel;
        if (port != 443) {
            ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(address, port).usePlaintext();
            channel = channelBuilder.build();
        } else {
            ChannelCredentials credentials = TlsChannelCredentials.newBuilder()
                    //You can use your own certificate here .trustManager(new File("cert.pem"))
                    .trustManager(InsecureTrustManagerFactory.INSTANCE.getTrustManagers())
                    .build();
            channel = Grpc.newChannelBuilderForAddress(address, port, credentials)
                    .build();
        }
        this.stub = UserServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public Optional<User> findUserByID(UserID id) {
        SearchRequest searchRequest = SearchRequest.newBuilder().setUser(id.toString()).build();

        com.rlgino.CardsService.infrastructure.usersproto.User user = stub.searchUser(searchRequest);

        if (user == null || user.getId().isEmpty()) return Optional.empty();

        return Optional.of(new User());
    }
}
