package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Brand;
import com.rlgino.CardsService.domain.calculator.Calculator;
import com.rlgino.CardsService.domain.calculator.CalculatorFactory;
import com.rlgino.CardsService.domain.exception.NotBrandCardException;
import com.rlgino.CardsService.domain.Percentage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxInterestExecutor {
    private final LocalDate calendar;

    public TaxInterestExecutor(LocalDate calendar) {
        this.calendar = calendar;
    }

    public Percentage calculate(Brand brand) throws NotBrandCardException {
        final Calculator calculator = CalculatorFactory.getTaxCalculator(brand);
        return calculator.calculate(this.calendar);
    }
}
