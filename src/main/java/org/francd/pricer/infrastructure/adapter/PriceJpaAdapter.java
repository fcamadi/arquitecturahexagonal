package org.francd.pricer.infrastructure.adapter;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.domain.port.PricePersistencePort;
import org.francd.pricer.infrastructure.entity.PriceEntity;
import org.francd.pricer.infrastructure.mapper.PriceMapper;
import org.francd.pricer.infrastructure.repository.PriceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component("PriceJpaAdapter")
public class PriceJpaAdapter implements PricePersistencePort {

    @Autowired
    private PriceJpaRepository priceJpaRepository;


    @Override
    public List<Price> findAll() {
        return PriceMapper.convertToDomainList(priceJpaRepository.findAll());
    }


    @Override
    public List<Price> findByApplicationDateBrandIdAndProductId(LocalDateTime applicationDate, Integer brandId, Integer productId) {
        List<PriceEntity> entityList = priceJpaRepository.findByApplicationDateBrandIdAndProductId(applicationDate, brandId, productId);
        List<Price> prices = PriceMapper.convertToDomainList(entityList);
        return prices;
    }



}
