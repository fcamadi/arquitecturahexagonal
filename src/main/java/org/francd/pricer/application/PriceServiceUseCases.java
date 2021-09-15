package org.francd.pricer.application;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.domain.service.PriceService;

import java.time.LocalDateTime;
import java.util.List;

public class PriceServiceUseCases {

    private PriceService priceService;

    public PriceServiceUseCases(PriceService priceService) {
        this.priceService = priceService;
    }

    public List<Price> findAll() {
        return priceService.findAll();
    }

    public Price findByApplicationDateBrandIdAndProductId(
            LocalDateTime applicationDate, Integer brandId, Integer productId) {
        return priceService.findByApplicationDateBrandIdAndProductId(applicationDate,brandId,productId);
    }

}

