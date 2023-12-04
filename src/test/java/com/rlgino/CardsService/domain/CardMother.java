package com.rlgino.CardsService.domain;

import com.rlgino.CardsService.domain.users.UserID;

public class CardMother {
    public static Card createCardRandom(){
        final CardNumber cardNumber = CardNumberMother.random();
        final CardHolder holder = new CardHolder("test", "test");
        final CardDueDate dueDate = CardDueDate.from("02/2025");
        final UserID userID = UserIDMother.generate();
        return new Card(cardNumber, Brand.VISA, holder, dueDate, userID);
    }

    public static Card createDueCard(){
        final CardNumber cardNumber = CardNumberMother.random();
        final CardHolder holder = new CardHolder("test", "test");
        final CardDueDate dueDate = CardDueDate.from("02/2000");
        final UserID userID = UserIDMother.generate();
        return new Card(cardNumber, Brand.VISA, holder, dueDate, userID);
    }
}
