package com.catalogue.cleanarchitecture.application.config;


import com.catalogue.cleanarchitecture.domain.usecase.GetPriceUseCase;
import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.repository.PriceDboRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootServiceConfig {

    @Bean
    public GetPriceUseCase getUserUseCase(PriceDboRepository priceDboRepository) {
        return new GetPriceUseCase(priceDboRepository);
    }
}