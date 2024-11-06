package com.rlgino.CardsService.infrastructure.controller;

import com.rlgino.CardsService.application.CardCreator;
import com.rlgino.CardsService.application.CardFinder;
import com.rlgino.CardsService.domain.*;
import com.rlgino.CardsService.domain.exception.CardNotFoundException;
import com.rlgino.CardsService.domain.users.UserID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/card")
@Tag(name = "card", description = "Manage card objects")
public class CardController {

    private final CardCreator cardCreator;
    private final CardFinder cardFinder;

    @Autowired
    public CardController(CardCreator cardCreator, CardFinder cardFinder) {
        this.cardCreator = cardCreator;
        this.cardFinder = cardFinder;
    }

    @Operation(summary = "Getting a card by ID",
            description = "Getting all details of a card",
            tags = { "cards", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = CardDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findCard(@PathVariable @Schema(example = "95854717-f395-455e-b610-95bd85d9adb1") String id) {
        try {
            final CardNumber cardNumber = new CardNumber(id);
            Card cardResult = this.cardFinder.call(cardNumber);
            CardDTO response = new CardDTO(cardResult.cardNumber().toString(), cardResult.brand().toString(), cardResult.cardHolder().toString(), "", cardResult.dueDate().toString(), "TBD");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Creating a card",
            description = "Creating a card with required details",
            tags = { "cards", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = CardDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
    })
    @Parameter(description = "Auth token", required = true, name = "auth", in = ParameterIn.HEADER)
    @PostMapping
    public ResponseEntity<String> CreateCard(
            @RequestBody CardDTO createCardRequest) {
        try {
            final CardNumber cardNumber = new CardNumber(createCardRequest.getCardNumber());
            if (createCardRequest.getBrand() == null)
                return new ResponseEntity<>("Marca inválida", HttpStatus.BAD_REQUEST);
            final Brand brand = Brand.valueOf(createCardRequest.getBrand());
            final CardHolder cardHolder = new CardHolder(createCardRequest.getName(), createCardRequest.getLastName());
            final UserID userID = UserID.from(createCardRequest.getUserID());
            cardCreator.Execute(new Card(cardNumber, brand, cardHolder, CardDueDate.from(createCardRequest.getDueDate()), userID));
        } catch (IllegalArgumentException e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity<>("Marca inválida", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

@Getter
final class CardDTO {
    @Schema(example = "95854717-f395-455e-b610-95bd85d9adb1", format = "uuid", type = "string")
    private String cardNumber;
    @Schema(type = "string", allowableValues = { "VISA", "NARA", "AMEX" })
    private String brand;
    private String name;
    private String lastName;
    @Schema(example = "04/2024")
    private String dueDate;
    @Schema(example = "95854717-f395-455e-b610-95bd85d9adb1", format = "uuid", type = "string")
    private String userID;

    public CardDTO(String cardNumber, String brand, String name, String lastName, String date, String userID) {
        this.cardNumber = cardNumber;
        this.brand = brand;
        this.name = name;
        this.lastName = lastName;
        this.dueDate = date;
        this.userID = userID;
    }

}
