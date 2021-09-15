package org.francd.pricer.infrastructure.repository;

import org.francd.pricer.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> findAllByProductId(Integer productId);

    @Query("SELECT p FROM PriceEntity p WHERE"
            + " (:applicationDate BETWEEN p.startDate AND p.endDate)"
            + " AND (:brandId =  p.brandId)"
            + " AND (:productId =  p.productId)"
    )
    List<PriceEntity> findByApplicationDateBrandIdAndProductId(LocalDateTime applicationDate, Integer brandId, Integer productId);
}