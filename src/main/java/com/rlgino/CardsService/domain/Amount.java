package com.rlgino.CardsService.domain;

import java.math.BigDecimal;

public record Amount(BigDecimal amountValue) {
    public Amount(BigDecimal amountValue) {
        this.amountValue = amountValue;
        if (!isValid()) {
            throw new RuntimeException("Invalid amount");
        }
    }

    public Boolean isValid() {
        return amountValue.compareTo(BigDecimal.ZERO) > 0;
    }
}
