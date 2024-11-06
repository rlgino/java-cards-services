package com.rlgino.CardsService.domain;

import com.rlgino.CardsService.domain.users.UserID;

public record Card (CardNumber cardNumber, Brand brand, CardHolder cardHolder, CardDueDate dueDate, UserID userID) {

    public Boolean isValidToOperate() {
        return dueDate.isValidToOperate();
    }

}
