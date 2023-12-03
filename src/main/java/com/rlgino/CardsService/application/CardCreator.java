package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.events.CardCreatedEvent;
import com.rlgino.CardsService.domain.CardRepository;

public class CardCreator {

    private final CardRepository repository;
    private final CardCreatedEvent event;

    public CardCreator(CardRepository repository, CardCreatedEvent cardCreatedEvent) {
        this.repository = repository;
        this.event = cardCreatedEvent;
    }

    public void Execute(Card card){
        this.repository.SaveCard(card);

        event.Send(card);
    }
}
