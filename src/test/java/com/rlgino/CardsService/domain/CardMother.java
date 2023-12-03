package com.rlgino.CardsService.domain;

public class CardMother {
    public static Card createCardRandom(){
        final CardNumber cardNumber = CardNumberMother.random();
        final CardHolder holder = new CardHolder("test", "test");
        final CardDueDate dueDate = CardDueDate.from("02/2025");
        return new Card(cardNumber, Brand.VISA, holder, dueDate);
    }

    public static Card createDueCard(){
        final CardNumber cardNumber = CardNumberMother.random();
        final CardHolder holder = new CardHolder("test", "test");
        final CardDueDate dueDate = CardDueDate.from("02/2000");
        return new Card(cardNumber, Brand.VISA, holder, dueDate);
    }
}
