package com.rlgino.CardsService.infrastructure.controller;

import com.rlgino.CardsService.domain.Brand;
import com.rlgino.CardsService.application.TaxInterestExecutor;
import com.rlgino.CardsService.domain.Percentage;
import com.rlgino.CardsService.domain.exception.NotBrandCardException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final TaxInterestExecutor taxCalculator;

    @Autowired
    public OperationController(TaxInterestExecutor taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @GetMapping("/interest")
    public ResponseEntity<OperationResponseDTO> calculateInterest(@RequestBody OperationDTO operation) {
        final Brand brand = Brand.valueOf(operation.getBrand());
        final OperationResponseDTO response = new OperationResponseDTO();
        try {
            final Percentage interestPercentage = this.taxCalculator.calculate(brand);
            response.setResult(interestPercentage.calculate(operation.getAmount()));
        } catch (NotBrandCardException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

@AllArgsConstructor
@Getter
class OperationDTO {
    private String brand;
    private BigDecimal amount;
}
@Getter
@Setter
class OperationResponseDTO {
    private BigDecimal result;
    private String message;
}
