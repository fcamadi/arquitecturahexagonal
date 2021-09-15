package org.francd.pricer.infrastructure.mapper;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.infrastructure.entity.PriceEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PriceMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Constructor
     */
    public PriceMapper() {
        modelMapper.getConfiguration().setAmbiguityIgnored(false);
    }


    /**
     * Takes domain entity and uses it to create a JPA entity.
     *
     * @param price the domain entity to convert into a JPA entity
     * @return the JPA entity
     */
    public static PriceEntity convertToEntity(Price price) {
        return modelMapper.map(price, PriceEntity.class);
    }

    /**
     * Extract meaningful values from JPA entity and creates a domain entity.
     *
     * @param price the JPA entity to convert to domain entity
     * @return the domain entity
     */
    public static Price convertToDomain(PriceEntity price) {
        return modelMapper.map(price, Price.class);
    }

    /**
     * Transforms a list of domain entities in a list of JPA entities
     * @param priceList
     * @return the list of JPA entities
     */
    public static List<PriceEntity> convertToEntityList(List<Price> priceList) {
        return priceList.stream().map(dto -> modelMapper.map(dto, PriceEntity.class)).collect(Collectors.toList());
    }

    /**
     * Transforms a list of JPA entities in a list of domain entities
     * @param priceList
     * @return the list of domain entities
     */
    public static List<Price> convertToDomainList(List<PriceEntity> priceList) {
        return priceList.stream().map( e -> modelMapper.map(e, Price.class)).collect(Collectors.toList());
    }

}
