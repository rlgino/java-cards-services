package com.rlgino.CardsService.infrastructure.persistence.postgres;

import com.rlgino.CardsService.domain.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "CARD")
public class CardDTO {
    @Id
    @Column(name = "NUMBER")
    private String cardNumber;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "DUE_DATE")
    private String dueDate;

    public static CardDTO fromCard(Card card) {
        final CardDTO cardResult = new CardDTO();
        cardResult.setCardNumber(card.cardNumber().toString());
        cardResult.setBrand(card.brand().name());
        String holder = card.cardHolder().toString();
        String[] fullName = holder.split(" ");
        cardResult.setName(fullName[0]);
        cardResult.setLastName(fullName[1]);
        cardResult.setDueDate(card.dueDate().toString());
        return cardResult;
    }

    public static Card parse(CardDTO cardDTO) {
        final CardNumber cardNumber = new CardNumber(cardDTO.getCardNumber());
        final Brand brand = Brand.valueOf(cardDTO.getBrand());
        final CardHolder cardHolder = new CardHolder(cardDTO.getName(), cardDTO.getLastName());
        final CardDueDate cardDueDate = CardDueDate.from(cardDTO.dueDate);
        return new Card(cardNumber, brand, cardHolder, cardDueDate);
    }
}
