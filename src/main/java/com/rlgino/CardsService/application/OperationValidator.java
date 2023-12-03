package com.rlgino.CardsService.application;

import com.rlgino.CardsService.domain.Operation;

public class OperationValidator {
    public Boolean validator(Operation operation) {
        return operation.isValid();
    }
}
