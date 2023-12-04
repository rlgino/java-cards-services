package com.rlgino.CardsService.infrastructure.persistence.postgres;

import com.rlgino.CardsService.domain.*;
import com.rlgino.CardsService.domain.users.UserID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
    @Column(name = "USER_ID")
    private String userID;

    public static CardDTO fromCard(Card card) {
        final CardDTO cardResult = new CardDTO();
        cardResult.setCardNumber(card.cardNumber().toString());
        cardResult.setBrand(card.brand().name());
        String holder = card.cardHolder().toString();
        String[] fullName = holder.split(" ");
        cardResult.setName(fullName[0]);
        cardResult.setLastName(fullName[1]);
        cardResult.setDueDate(card.dueDate().toString());
        cardResult.setUserID(card.userID().toString());
        return cardResult;
    }

    public static Card parse(CardDTO cardDTO) {
        final CardNumber cardNumber = new CardNumber(cardDTO.getCardNumber());
        final Brand brand = Brand.valueOf(cardDTO.getBrand());
        final CardHolder cardHolder = new CardHolder(cardDTO.getName(), cardDTO.getLastName());
        final CardDueDate cardDueDate = CardDueDate.from(cardDTO.dueDate);
        final UserID userID = UserID.from(cardDTO.userID);
        return new Card(cardNumber, brand, cardHolder, cardDueDate, userID);
    }
}
