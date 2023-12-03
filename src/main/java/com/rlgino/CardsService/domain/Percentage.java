package com.rlgino.CardsService.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Percentage {
    private final BigDecimal percentage;

    public Percentage(BigDecimal value) {
        this.percentage = value;
    }

    public BigDecimal calculate(BigDecimal amount) {
        return amount.multiply(percentage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Percentage that = (Percentage) o;
        return Objects.equals(percentage, that.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentage);
    }
}
