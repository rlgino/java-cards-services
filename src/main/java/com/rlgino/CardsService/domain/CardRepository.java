package com.rlgino.CardsService.domain;

import java.util.Optional;

public interface CardRepository {
    Optional<Card> GetCardByNumber(CardNumber number);
    void SaveCard(Card card);
}
