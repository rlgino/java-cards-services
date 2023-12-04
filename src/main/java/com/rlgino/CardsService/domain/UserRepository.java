package com.rlgino.CardsService.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByID(String id);
}
