package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Brand;
import com.rlgino.CardsService.domain.Percentage;
import com.rlgino.CardsService.domain.exception.NotBrandCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxInterestExecutorShouldTest {
    private TaxInterestExecutor executor;
    final LocalDate calendar =  LocalDate.of(2023, 2, 27);

    @BeforeEach
    public void setup() {
        this.executor = new TaxInterestExecutor(calendar);
    }

    @Test
    public void CalculateInterestForVisaCard() throws NotBrandCardException {
        Percentage interest = this.executor.calculate(Brand.VISA);
        // Year / Month = 25/2
        assertEquals( new Percentage(BigDecimal.valueOf(23/2)), interest);
    }

    @Test
    public void CalculateInterestForAmexCard() throws NotBrandCardException {
        Percentage interest = this.executor.calculate(Brand.AMEX);
        // Month * 0.1 = 2 * 0.1
        assertEquals( new Percentage(BigDecimal.valueOf(2*0.1)), interest);
    }

    @Test
    public void CalculateInterestForNaraCard() throws NotBrandCardException {
        Percentage interest = this.executor.calculate(Brand.NARA);
        // Day of month * 0.5 = 27 * 0.5
        assertEquals( new Percentage(BigDecimal.valueOf(calendar.getDayOfMonth()*0.5)), interest);
    }
}
