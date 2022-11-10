package com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.mapper;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.springdata.dbo.PriceEntity;

public class PriceMapper {

    private PriceMapper() {
    }

    public static Price toDomain(PriceEntity entity) {
        Price price = new Price();
        price.setPriceList(entity.getPriceList());
        price.setBrandId(entity.getBrandId());
        price.setProductId(entity.getProductId());
        price.setStartDate(entity.getStartDate());
        price.setEndDate(entity.getEndDate());
        price.setPriority(entity.getPriority());
        price.setPrice(entity.getPrice());
        price.setCurr(entity.getCurr());
        return price;
    }

    public static PriceEntity toDbo(Price price) {
        PriceEntity entity = new PriceEntity();
        entity.setPriceList(price.getPriceList());
        entity.setBrandId(price.getBrandId());
        entity.setProductId(price.getProductId());
        entity.setStartDate(price.getStartDate());
        entity.setEndDate(price.getEndDate());
        entity.setPriority(price.getPriority());
        entity.setPrice(price.getPrice());
        entity.setCurr(price.getCurr());
        return entity;
    }
}
