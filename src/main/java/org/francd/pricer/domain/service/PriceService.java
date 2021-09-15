package org.francd.pricer.domain.service;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.domain.port.PricePersistencePort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


public class PriceService {

    final private PricePersistencePort pricePersistencePort;

    public PriceService(PricePersistencePort pricePersistencePort) {
        this.pricePersistencePort = Objects.requireNonNull(pricePersistencePort);
    }


    public List<Price> findAll() {
        return pricePersistencePort.findAll();
    }


    public Price findByApplicationDateBrandIdAndProductId(LocalDateTime applicationDate, Integer brandId, Integer productId) {
        List<Price> prices = pricePersistencePort.findByApplicationDateBrandIdAndProductId(applicationDate, brandId, productId);
        return findPriceWithHighestPriority(prices);
    }

    /**
     * Auxiliary method used by findByApplicationDateBrandIdAndProductId
     */
    private Price findPriceWithHighestPriority(List<Price> priceList) {

        if (priceList.size()==1) {
            return priceList.get(0);
        }
        //if there is more than one, return the price with the highest priority
        else {
            return priceList.stream().max(Comparator.comparing(Price::getPriority)).orElseThrow(NoSuchElementException::new);
        }
    }
}
