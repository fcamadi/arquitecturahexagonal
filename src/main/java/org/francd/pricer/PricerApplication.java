package org.francd.pricer;

import org.francd.pricer.infrastructure.csv.CSVImporter;
import org.francd.pricer.infrastructure.entity.PriceEntity;
import org.francd.pricer.infrastructure.repository.PriceJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class PricerApplication {

    private static final Logger log = LoggerFactory.getLogger(PricerApplication.class);

    private final PriceJpaRepository priceRepository;

    public PricerApplication(PriceJpaRepository priceRepository) {
        this.priceRepository = Objects.requireNonNull(priceRepository);
    }


    public static void main(String[] args) {
        SpringApplication.run(PricerApplication.class, args);
    }



    public static void loadDataFromCSV(PriceJpaRepository priceRepository) {
        CSVImporter csvImport = new CSVImporter();
        List<PriceEntity> priceList = csvImport.importCSV("prices.csv");
        for (PriceEntity price: priceList) {
            priceRepository.save(price);
        }
        List<PriceEntity> databaseList = priceRepository.findAll();
        for (PriceEntity p : databaseList) {
            log.info("Price : {} ", p);
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(PriceJpaRepository priceRepository) {
        return args ->
                loadDataFromCSV(priceRepository);

    }

}
