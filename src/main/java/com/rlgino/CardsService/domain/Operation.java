package com.rlgino.CardsService.domain;

import java.util.Objects;

public record Operation (Card card, Amount operationAmount) {

    public Operation{
        Objects.requireNonNull(card, "Card couldn't be null");
        Objects.requireNonNull(operationAmount, "Operation amount couldn't be null");
    }

    public Boolean isValid() {
        return this.operationAmount.isValid() && this.card.isValidToOperate();
    }
}
