package com.rlgino.CardsService.domain.calculator;

import com.rlgino.CardsService.domain.Percentage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Amex implements Calculator{
    @Override
    public Percentage calculate(LocalDate date) {
        BigDecimal interestTax = BigDecimal.valueOf(date.getMonth().getValue() * 0.1);
        return new Percentage(interestTax);
    }
}
