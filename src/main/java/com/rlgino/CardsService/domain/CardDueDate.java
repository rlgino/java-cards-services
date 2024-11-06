package com.rlgino.CardsService.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record CardDueDate (LocalDate dueDate) {
    public final static String LAYOUT = "MM/yyyy";
    public final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(LAYOUT);

    public static CardDueDate from(String dueDate) {
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/" + LAYOUT);
        final LocalDate formattedDueDate = LocalDate.parse("01/" + dueDate, FORMATTER);
        return new CardDueDate(formattedDueDate);
    }

    public Boolean isValidToOperate() {
        return dueDate.isAfter(LocalDate.now()); //TODO: Cast new local date
    }

    @Override
    public String toString() {
        return this.dueDate.format(FORMATTER);
    }
}
