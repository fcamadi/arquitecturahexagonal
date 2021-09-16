package org.francd.pricer.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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


    @Operation(summary = "Return all prices.")
    @GetMapping("")
    public List<Price> getAllPrices() {
        return findAllPrices.execute();
    }

    @Operation(summary = "Get a price by application date, brand id and product id. " +
            "If there is more than one, the one with the highest priority is returned.")
    @GetMapping("/{applicationDate}/{brandId}/{productId}")
    public Price getPriceByApplicationDateBrandIdAndProductId(
            @Parameter(description = "Application date for the price. Format: yyyy-MM-dd-HH.mm.ss")
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") @PathVariable  LocalDateTime applicationDate,
            @Parameter(description = "Id of the product's brand. An integer.") @PathVariable Integer brandId,
            @Parameter(description = "Id of the product. An integer.")@PathVariable Integer productId) {
        return findPriceByApplicationDateBrandIdAndProductId.execute(applicationDate,brandId,productId);
    }

}
