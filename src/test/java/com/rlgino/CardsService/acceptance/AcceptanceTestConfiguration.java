package com.rlgino.CardsService.acceptance;

import com.rlgino.CardsService.domain.users.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class AcceptanceTestConfiguration {
    @Primary
    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }
}
