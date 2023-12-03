package com.rlgino.CardsService.infrastructure;

import com.rlgino.CardsService.application.TaxInterestExecutor;
import com.rlgino.CardsService.domain.events.CardCreatedEvent;
import com.rlgino.CardsService.application.CardCreator;
import com.rlgino.CardsService.application.CardFinder;
import com.rlgino.CardsService.domain.CardRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@Configuration
@EntityScan("com.rlgino.CardsService.infrastructure.persistence.*")
@EnableJpaRepositories("com.rlgino.CardsService.infrastructure.persistence.*")
public class BeanConfiguration {
    @Bean
    public CardCreator cardCreator(CardRepository cardRepositoryPostgres, CardCreatedEvent cardCreatorNotification){
        return new CardCreator(cardRepositoryPostgres, cardCreatorNotification);
    }

    @Bean
    public CardFinder cardFinder(CardRepository cardRepositoryPostgres){
        return new CardFinder(cardRepositoryPostgres);
    }

    @Bean
    public TaxInterestExecutor taxInterestExecutor(){
        return new TaxInterestExecutor(LocalDate.now());
    }
}
