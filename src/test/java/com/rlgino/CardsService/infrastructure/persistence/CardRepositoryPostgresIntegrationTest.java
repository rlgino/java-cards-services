package com.rlgino.CardsService.infrastructure.persistence;

import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.CardMother;
import com.rlgino.CardsService.domain.CardNumber;
import com.rlgino.CardsService.infrastructure.persistence.postgres.CardRepositoryPostgres;
import com.rlgino.CardsService.infrastructure.PostgresContainerTest;
import com.rlgino.CardsService.domain.CardNumberMother;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardRepositoryPostgresIntegrationTest extends PostgresContainerTest {
    @Autowired
    CardRepositoryPostgres cardRepository;

    @Test
    @Transactional
    public void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationJPQL_ThenModifyMatchingUsers() {
        insertCards();
        final CardNumber cardNumber = CardNumberMother.random();
        Optional<Card> card = cardRepository.GetCardByNumber(cardNumber);
        assertTrue(card.isPresent());
    }

    private void insertCards() {
        cardRepository.SaveCard(CardMother.createCardRandom());
    }
}
