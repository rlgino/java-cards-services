package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Brand;
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
        switch (brand){
            case VISA:
                return executeVisaCalculator();
            case AMEX:
                return executeAmexCalculator();
            case NARA:
                return executeNaraCalculator();
            default:
                throw new NotBrandCardException(brand.getName());
        }
    }

    private Percentage executeNaraCalculator() {
        BigDecimal interestTax = BigDecimal.valueOf(this.calendar.getDayOfMonth() * 0.5);
        return new Percentage(interestTax);
    }

    private Percentage executeAmexCalculator() {
        BigDecimal interestTax = BigDecimal.valueOf(this.calendar.getMonth().getValue() * 0.1);
        return new Percentage(interestTax);
    }

    private Percentage executeVisaCalculator() {
        int year = this.calendar.getYear()%2000;
        BigDecimal interestTax = BigDecimal.valueOf(year / this.calendar.getMonth().getValue());
        return new Percentage(interestTax);
    }
}
