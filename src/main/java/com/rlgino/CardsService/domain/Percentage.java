package com.rlgino.CardsService.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Percentage (BigDecimal percentage) {
    public Percentage {
        Objects.requireNonNull(percentage, "Percentage couldn't be null");
    }

    public BigDecimal calculate(BigDecimal amount) {
        return amount.multiply(percentage);
    }
}
