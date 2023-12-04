package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.users.User;
import com.rlgino.CardsService.domain.users.UserRepository;
import com.rlgino.CardsService.domain.events.CardCreatedEvent;
import com.rlgino.CardsService.domain.CardRepository;
import com.rlgino.CardsService.domain.exception.UserNotFoundException;

import java.util.Optional;

public class CardCreator {

    private final CardRepository repository;
    private final UserRepository userRepository;
    private final CardCreatedEvent event;

    public CardCreator(CardRepository repository, UserRepository userRepository, CardCreatedEvent cardCreatedEvent) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.event = cardCreatedEvent;
    }

    public void Execute(Card card, String userID){
        Optional<User> userByID = this.userRepository.findUserByID(userID);
        if (userByID.isEmpty()) throw new UserNotFoundException(userID);
        this.repository.SaveCard(card);

        event.Send(card);
    }
}
