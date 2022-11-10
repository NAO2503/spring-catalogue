package com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.repository;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.ports.PriceRepository;
import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.mapper.PriceMapper;
import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.springdata.dbo.PriceEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Component
public class PriceDboRepository implements PriceRepository {

    private final SpringDataPriceRepository priceRepository;

    @Override
    public Price findByPriceList(Long priceList) {
        PriceEntity entity = priceRepository.findByPriceList(priceList).orElse(null);
        if (entity != null) {
            return PriceMapper.toDomain(entity);
        }
        return null;
    }

    @Override
    public List<Price> findAllByBrandIdAndProductIdBetweenDates(Long brandId, Long productId, Date dateBetween) {
        List<Price> prices = new ArrayList<>();
        List<PriceEntity> entities = priceRepository.findAllByBrandIdAndProductIdBetweenDates(brandId, productId, dateBetween)
                .orElse(new ArrayList<>());
        entities.forEach(entity -> prices.add(PriceMapper.toDomain(entity)));
        return prices;
    }
}
