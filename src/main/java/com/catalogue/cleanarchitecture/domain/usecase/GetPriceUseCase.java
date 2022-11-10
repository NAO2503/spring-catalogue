package com.catalogue.cleanarchitecture.domain.usecase;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.ports.PriceRepository;
import com.catalogue.cleanarchitecture.domain.usecase.util.DateUtil;
import com.catalogue.cleanarchitecture.domain.usecase.util.ListPriceUtil;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class GetPriceUseCase {

    private final PriceRepository priceRepository;

    public Price getPrice(Long priceList) {
        return priceRepository.findByPriceList(priceList);
    }

    public Price findByBrandProductBetweenDate(Long brandId, Long productId, String dateBetween) {
        List<Price> prices = listAllByBrandProductBetweenDate(brandId, productId, dateBetween);
        if (!prices.isEmpty()) {
            if (prices.size() > 1) {
                //Se dejan los elementos de mayor prioridad
                prices = ListPriceUtil.filterSimilarElementsByPrority(prices);
                //Si existe mas de uno aún, se dejara al elemento con mayor priceList
                prices = ListPriceUtil.filterSimilarElementsByPriceList(prices);
                //Se devuelve un unico objeto
                return prices.get(0);
            }
            return prices.get(0);
        }
        return null;
    }

    public List<Price> listAllByBrandProductBetweenDate(Long brandId, Long productId, String dateBetween) {
        Date dateToDateObj = DateUtil.getDateFromString(dateBetween);
        if (dateToDateObj == null) {
            return new ArrayList<>();
        }
        return priceRepository.findAllByBrandIdAndProductIdBetweenDates(brandId, productId, dateToDateObj);
    }
}
