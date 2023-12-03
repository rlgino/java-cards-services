package com.rlgino.CardsService.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardDueDate {
    private final LocalDate dueDate;
    public final static String LAYOUT = "MM/yyyy";
    public final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(LAYOUT);

    private CardDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

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

    public int getYear() {
        return this.dueDate.getYear()%2000; //TODO: Review
    }

    public int getMonth() {
        return this.dueDate.getMonth().getValue();
    }
}
