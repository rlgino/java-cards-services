package com.rlgino.CardsService.domain;

public class CardHolder {
    private final String name;
    private final String lastName;

    public CardHolder(String name, String lastName) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name couldn't be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new RuntimeException("Last name couldn't be empty");
        }
        this.name = name;
        this.lastName = lastName;
    }

    public String getCardHolder() {
        return name + " " + lastName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
