package com.rlgino.CardsService.infrastructure.persistence.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSQLRepository extends CrudRepository<CardDTO, String> {
}
