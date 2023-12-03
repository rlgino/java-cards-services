package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.CardMother;
import com.rlgino.CardsService.domain.CardRepository;
import com.rlgino.CardsService.domain.events.CardCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CardCreatorShouldTest {
    private CardCreator creator;
    private CardRepository cardRepository;
    private CardCreatedEvent event;

    @BeforeEach
    public void setup(){
        this.cardRepository = mock(CardRepository.class);
        this.event = mock(CardCreatedEvent.class);
        this.creator = new CardCreator(this.cardRepository, this.event);
    }

    @Test
    public void createACardSuccessfully(){
        Card card = CardMother.createCardRandom();

        this.creator.Execute(card);

        verify(this.cardRepository, times(1)).SaveCard(card);
        verify(this.event, times(1)).Send(card);
    }
}
