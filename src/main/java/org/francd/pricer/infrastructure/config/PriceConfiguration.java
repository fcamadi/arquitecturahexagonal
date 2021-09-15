package org.francd.pricer.infrastructure.config;

import org.francd.pricer.application.PriceServiceUseCases;
import org.francd.pricer.domain.port.PricePersistencePort;
import org.francd.pricer.domain.service.PriceService;
import org.francd.pricer.infrastructure.adapter.PriceJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PriceConfiguration {

    @Bean
    public PricePersistencePort pricePersistence(){
        return new PriceJpaAdapter();
    }

    @Bean
    public PriceService priceService(){
        return new PriceService(pricePersistence());
    }

    @Bean
    public PriceServiceUseCases priceServiceUseCases() {
        return new PriceServiceUseCases(priceService());
    }

}
