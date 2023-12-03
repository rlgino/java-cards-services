package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.*;
import com.rlgino.CardsService.domain.exception.CardNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CardFinderShouldTest {
    private CardFinder cardFinder;
    private CardRepository cardRepository;

    @BeforeEach
    public void setup(){
        cardRepository = mock(CardRepository.class);
        cardFinder = new CardFinder(cardRepository);
    }

    @Test
    public void findOneCard() {
        CardNumber cardNumber = CardNumberMother.random();
        Card expectedCard = CardMother.createCardRandom();
        when(this.cardRepository.GetCardByNumber(cardNumber)).thenReturn(Optional.of(expectedCard));

        Card call = this.cardFinder.call(cardNumber);

        verify(cardRepository).GetCardByNumber(cardNumber);
        assertThat(call).isEqualTo(expectedCard);
    }

    @Test
    public void notFoundCard() {
        CardNumber cardNumber = CardNumberMother.random();
        when(this.cardRepository.GetCardByNumber(cardNumber)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(CardNotFoundException.class, () -> this.cardFinder.call(cardNumber));

        assertEquals("Card not found", exception.getMessage());
    }
}
