package org.francd.pricer.infrastructure.controller;

import org.francd.pricer.application.FindAllPrices;
import org.francd.pricer.application.FindPriceByApplicationDateBrandIdAndProductId;
import org.francd.pricer.domain.model.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/prices")
public class PriceController {

    private final FindAllPrices findAllPrices;
    private final FindPriceByApplicationDateBrandIdAndProductId findPriceByApplicationDateBrandIdAndProductId;

    public PriceController(FindAllPrices findAllPrices,
                           FindPriceByApplicationDateBrandIdAndProductId findPriceByApplicationDateBrandIdAndProductId) {
        this.findAllPrices = Objects.requireNonNull(findAllPrices);
        this.findPriceByApplicationDateBrandIdAndProductId = Objects.requireNonNull(findPriceByApplicationDateBrandIdAndProductId);
    }


    @GetMapping("")
    public List<Price> getAllPrices() {
        return findAllPrices.execute();
    }


    @GetMapping("/{applicationDate}/{brandId}/{productId}")
    public Price getPriceByApplicationDateBrandIdAndProductId(
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") @PathVariable  LocalDateTime applicationDate,
            @PathVariable Integer brandId, @PathVariable Integer productId) {
        return findPriceByApplicationDateBrandIdAndProductId.execute(applicationDate,brandId,productId);
    }

}
