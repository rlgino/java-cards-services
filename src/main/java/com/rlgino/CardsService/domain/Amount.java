package com.rlgino.CardsService.domain;

import java.math.BigDecimal;

public class Amount {
    private final BigDecimal amountValue;

    public Amount(BigDecimal amountValue) {
        this.amountValue = amountValue;
        if (!isValid()) {
            throw new RuntimeException("Invalid amount");
        }
    }

    public Boolean isValid() {
        return amountValue.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public String toString() {
        return this.amountValue.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Amount)) {
            return false;
        }
        Amount other = (Amount) obj;
        return other.amountValue.equals(this.amountValue);
    }
}
