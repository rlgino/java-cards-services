package com.rlgino.CardsService.domain;

public enum Brand {
    VISA("visa"), NARA("Tarjeta Naranja"), AMEX("American Express");


    private final String name;

    Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
