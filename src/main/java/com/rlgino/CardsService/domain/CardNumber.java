package com.rlgino.CardsService.domain;

public class CardNumber {
    private final String cardNumber;

    public CardNumber(String cardNumber) {
        if (cardNumber.length() < 16) throw new RuntimeException("Invalid Card Number length");
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return cardNumber;
    }
}
