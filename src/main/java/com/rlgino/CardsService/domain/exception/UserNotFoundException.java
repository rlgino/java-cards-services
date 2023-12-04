package com.rlgino.CardsService.domain.exception;

public class UserNotFoundException extends RuntimeException{
    private String userID;

    public UserNotFoundException(String userID) {
        this.userID = userID;
    }

    @Override
    public String getMessage() {
        return "User not found " + this.userID;
    }
}
