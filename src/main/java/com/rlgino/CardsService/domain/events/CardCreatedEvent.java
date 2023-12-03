package com.rlgino.CardsService.domain.events;

import com.rlgino.CardsService.domain.Card;

public interface CardCreatedEvent {
    void Send(Card card);
}
