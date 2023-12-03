package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Amount;
import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.CardMother;
import com.rlgino.CardsService.domain.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationValidatorShouldTest {

    private OperationValidator validator;

    @BeforeEach
    public void setup() {
        validator = new OperationValidator();
    }

    @Test
    public void isValidOperation(){
        Card card = CardMother.createCardRandom();
        Amount amount = new Amount(BigDecimal.TEN);
        Operation operation = new Operation(card, amount);
        Boolean isValid = validator.validator(operation);
        assertTrue(isValid);
    }

    @Test
    public void isNotOperationDueToDueCard(){
        Card card = CardMother.createDueCard();
        Amount amount = new Amount(BigDecimal.TEN);
        Operation operation = new Operation(card, amount);
        Boolean isValid = validator.validator(operation);
        assertFalse(isValid);
    }

    @Test
    public void isNotOperationDueToBigAmount(){
        Card card = CardMother.createDueCard();
        Amount amount = new Amount(BigDecimal.valueOf(1000));
        Operation operation = new Operation(card, amount);
        Boolean isValid = validator.validator(operation);
        assertFalse(isValid);
    }
}
