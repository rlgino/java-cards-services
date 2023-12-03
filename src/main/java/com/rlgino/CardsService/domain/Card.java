package com.rlgino.CardsService.domain;

public final class Card {
    private CardNumber cardNumber;
    private final Brand brand;
    private final CardHolder cardHolder;
    private final CardDueDate cardDueDate;

    public Card(CardNumber cardNumber, Brand brand, CardHolder cardHolder, CardDueDate cardDueDate) {
        this.cardNumber = cardNumber;
        this.brand = brand;
        this.cardHolder = cardHolder;
        this.cardDueDate = cardDueDate;
    }

    public Boolean isValidToOperate() {
        return cardDueDate.isValidToOperate();
    }

    public CardNumber cardNumber(){
        return cardNumber;
    }

    public Brand brand() {
        return brand;
    }

    public CardHolder cardHolder() {
        return cardHolder;
    }

    public CardDueDate dueDate() {
        return this.cardDueDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"cardNumber\":\"" + cardNumber +
                "\", \"brand\":\"" + brand +
                "\", \"cardHolder\":\"" + cardHolder +
                "\", \"cardDueDate\":\"" + cardDueDate +
                "\"}";
    }

}
