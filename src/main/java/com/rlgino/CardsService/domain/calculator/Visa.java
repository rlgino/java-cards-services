package com.rlgino.CardsService.domain.calculator;

import com.rlgino.CardsService.domain.Percentage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Visa implements Calculator{
    @Override
    public Percentage calculate(LocalDate date) {

        int year = date.getYear()%2000;
        BigDecimal interestTax = BigDecimal.valueOf(year / date.getMonth().getValue());
        return new Percentage(interestTax);
    }
}
