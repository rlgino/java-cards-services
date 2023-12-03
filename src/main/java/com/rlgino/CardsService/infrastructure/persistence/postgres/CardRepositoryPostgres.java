package com.rlgino.CardsService.infrastructure.persistence.postgres;


import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.CardNumber;
import com.rlgino.CardsService.domain.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("cardRepositoryPostgres")
public class CardRepositoryPostgres implements CardRepository {

    private CardSQLRepository cardSQLRepository;

    @Autowired
    public CardRepositoryPostgres(CardSQLRepository cardSQLRepository) {
        this.cardSQLRepository = cardSQLRepository;
    }

    @Override
    public Optional<Card> GetCardByNumber(CardNumber number) {
        Optional<CardDTO> cardOptional = this.cardSQLRepository.findById(number.toString());
        if(!cardOptional.isPresent()) return Optional.empty();
        final Card cardResult = CardDTO.parse(cardOptional.get());
        return Optional.of(cardResult);
    }

    @Override
    public void SaveCard(Card card) {
        final CardDTO cardDTO = CardDTO.fromCard(card);
        this.cardSQLRepository.save(cardDTO);
    }
}
