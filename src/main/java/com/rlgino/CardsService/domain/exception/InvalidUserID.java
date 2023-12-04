package com.rlgino.CardsService.domain.exception;

public class InvalidUserID extends RuntimeException{
    private String userID;

    public InvalidUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String getMessage() {
        return "User ID invalid " + this.userID;
    }
}
