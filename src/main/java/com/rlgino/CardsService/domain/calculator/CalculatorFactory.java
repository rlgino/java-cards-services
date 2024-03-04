package com.rlgino.CardsService.domain.calculator;

import com.rlgino.CardsService.domain.Brand;

public class CalculatorFactory {
    private CalculatorFactory() {}

    public static Calculator getTaxCalculator(Brand brand) {
        return switch (brand) {
            case VISA -> new Visa();
            case AMEX -> new Amex();
            case NARA -> new Nara();
        };
    }
}
