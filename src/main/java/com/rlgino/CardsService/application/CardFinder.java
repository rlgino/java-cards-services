package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.CardNumber;
import com.rlgino.CardsService.domain.exception.CardNotFoundException;
import com.rlgino.CardsService.domain.CardRepository;

import java.util.Optional;

public class CardFinder {

    private final CardRepository cardRepository;

    public CardFinder(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public Card call(CardNumber cardNumber) {
        Optional<Card> card = this.cardRepository.GetCardByNumber(cardNumber);

        return card.orElseThrow(CardNotFoundException::new);
    }
}
