package com.rlgino.CardsService.domain.users;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByID(String id);
}
