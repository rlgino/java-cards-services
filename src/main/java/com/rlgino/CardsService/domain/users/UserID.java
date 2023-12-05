package com.rlgino.CardsService.domain.users;

import com.rlgino.CardsService.domain.exception.InvalidUserID;

import java.util.UUID;

public class UserID {
    UUID userID;

    private UserID(String id) {
        try {
            userID = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidUserID(id);
        }
    }

    public static UserID from(String userID) {
        return new UserID(userID);
    }

    @Override
    public String toString() {
        return userID.toString();
    }

    @Override
    public int hashCode() {
        return userID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserID otherUserID)
            return this.userID.equals(otherUserID.userID);
        return false;
    }
}
