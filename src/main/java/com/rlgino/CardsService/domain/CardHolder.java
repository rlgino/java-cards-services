package com.rlgino.CardsService.domain;

public record CardHolder (String name, String lastName) {

    public CardHolder {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name couldn't be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new RuntimeException("Last name couldn't be empty");
        }
    }

    public String getCardHolder() {
        return name + " " + lastName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
