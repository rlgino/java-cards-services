package com.rlgino.CardsService.domain.exception;

public class NotBrandCardException extends Throwable {

    private final String name;

    public NotBrandCardException(String name) {
        this.name = name;
    }
    @Override
    public String getMessage() {
        return String.format("Card %s not found", this.name);
    }
}
