package org.francd.pricer.application;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.domain.service.PriceService;

import java.util.List;


public class FindAllPrices {

    private PriceService priceService;

    public FindAllPrices(PriceService priceService) {
        this.priceService = priceService;
    }

    public List<Price> execute() {
        return priceService.findAll();
    }

}
