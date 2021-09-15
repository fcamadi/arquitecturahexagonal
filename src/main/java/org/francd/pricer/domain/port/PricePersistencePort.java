package org.francd.pricer.domain.port;



import org.francd.pricer.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistencePort {

    List<Price> findAll();

    List<Price> findByApplicationDateBrandIdAndProductId(LocalDateTime applicationDate, Integer brandId, Integer productId);

}
