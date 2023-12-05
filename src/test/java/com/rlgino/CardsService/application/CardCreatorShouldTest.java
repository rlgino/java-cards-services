package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.*;
import com.rlgino.CardsService.domain.events.CardCreatedEvent;
import com.rlgino.CardsService.domain.exception.UserNotFoundException;
import com.rlgino.CardsService.domain.users.User;
import com.rlgino.CardsService.domain.users.UserID;
import com.rlgino.CardsService.domain.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardCreatorShouldTest {
    private CardCreator creator;
    private CardRepository cardRepository;
    private UserRepository userRepository;
    private CardCreatedEvent event;

    @BeforeEach
    public void setup(){
        this.cardRepository = mock(CardRepository.class);
        this.event = mock(CardCreatedEvent.class);
        this.userRepository = mock(UserRepository.class);
        this.creator = new CardCreator(this.cardRepository, userRepository, this.event);
    }

    @Test
    public void createACardSuccessfully(){
        Card card = CardMother.createCardRandom();
        when(this.userRepository.findUserByID(card.userID())).thenReturn(Optional.of(new User()));

        this.creator.Execute(card);

        verify(this.cardRepository, times(1)).SaveCard(card);
        verify(this.event, times(1)).Send(card);
    }

    @Test
    public void createACardNotUserFound(){
        Card card = CardMother.createCardRandom();
        when(this.userRepository.findUserByID(card.userID())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            this.creator.Execute(card);
        });

        String expectedMessage = "User not found 1c4425e2-308a-4ced-82f3-dd050342c8c7";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }
}
