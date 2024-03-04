package com.rlgino.CardsService.domain.calculator;

import com.rlgino.CardsService.domain.Percentage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Nara implements Calculator {
    @Override
    public Percentage calculate(LocalDate date) {
        BigDecimal interestTax = BigDecimal.valueOf(date.getDayOfMonth() * 0.5);
        return new Percentage(interestTax);
    }
}
