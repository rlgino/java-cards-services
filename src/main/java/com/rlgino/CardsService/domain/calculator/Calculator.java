package com.rlgino.CardsService.domain.calculator;

import com.rlgino.CardsService.domain.Percentage;

import java.time.LocalDate;

public interface Calculator {
    public Percentage calculate(LocalDate date);
}
