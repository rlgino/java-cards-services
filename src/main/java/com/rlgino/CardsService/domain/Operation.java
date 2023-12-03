package com.rlgino.CardsService.domain;

public final class Operation {
    private final Card card;
    private final Amount operationAmount;

    public Operation(Card card, Amount operationAmount) {
        this.card = card;
        this.operationAmount = operationAmount;
    }

    public Boolean isValid() {
        return this.operationAmount.isValid() && this.card.isValidToOperate();
    }
}
