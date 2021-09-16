package org.francd.pricer.application;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.domain.service.PriceService;

import java.time.LocalDateTime;


public class FindPriceByApplicationDateBrandIdAndProductId {

    private PriceService priceService;

    public FindPriceByApplicationDateBrandIdAndProductId(PriceService priceService) {
        this.priceService = priceService;
    }

    public Price execute(LocalDateTime applicationDate, Integer brandId, Integer productId) {
        return priceService.findByApplicationDateBrandIdAndProductId(applicationDate,brandId,productId);
    }

}

