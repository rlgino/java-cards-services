package com.rlgino.CardsService.domain;

public record CardNumber (String cardNumber) {

    public CardNumber {
        if (cardNumber.length() < 16) throw new RuntimeException("Invalid Card Number length");
    }

    @Override
    public String toString() {
        return cardNumber;
    }
}
