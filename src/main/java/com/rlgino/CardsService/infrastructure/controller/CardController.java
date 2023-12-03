package com.rlgino.CardsService.infrastructure.controller;

import com.rlgino.CardsService.application.CardCreator;
import com.rlgino.CardsService.application.CardFinder;
import com.rlgino.CardsService.domain.*;
import com.rlgino.CardsService.domain.exception.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardCreator cardCreator;
    private final CardFinder cardFinder;

    @Autowired
    public CardController(CardCreator cardCreator, CardFinder cardFinder) {
        this.cardCreator = cardCreator;
        this.cardFinder = cardFinder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findCard(@PathVariable String id) {
        try {
            final CardNumber cardNumber = new CardNumber(id);
            Card cardResult = this.cardFinder.call(cardNumber);
            CardDTO response = new CardDTO(cardResult.cardNumber().toString(), cardResult.brand().toString(), cardResult.cardHolder().toString(), "", cardResult.dueDate().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> CreateCard(@RequestBody CardDTO createCardRequest) {
        try {
            final CardNumber cardNumber = new CardNumber(createCardRequest.getCardNumber());
            if (createCardRequest.getBrand() == null)
                return new ResponseEntity<>("Marca inválida", HttpStatus.BAD_REQUEST);
            final Brand brand = Brand.valueOf(createCardRequest.getBrand());
            final CardHolder cardHolder = new CardHolder(createCardRequest.getName(), createCardRequest.getLastName());
            cardCreator.Execute(new Card(cardNumber, brand, cardHolder, CardDueDate.from(createCardRequest.getDate())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Marca inválida", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

final class CardDTO {
    private String cardNumber;
    private String brand;
    private String name;
    private String lastName;
    private String date;

    public CardDTO(String cardNumber, String brand, String name, String lastName, String date) {
        this.cardNumber = cardNumber;
        this.brand = brand;
        this.name = name;
        this.lastName = lastName;
        this.date = date;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public String getBrand() {
        return brand;
    }
}