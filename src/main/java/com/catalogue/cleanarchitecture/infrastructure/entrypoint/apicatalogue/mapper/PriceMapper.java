package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PriceMapper {

    private PriceMapper() {
    }

    public static List<PriceDto> listToDtoList(List<Price> prices) {
        List<PriceDto> priceDtos = new ArrayList<>();
        if (prices == null || prices.isEmpty()) {
            return priceDtos;
        }
        prices.forEach(price -> priceDtos.add(toDto(price)));
        return priceDtos;
    }

    public static PriceDto toDto(Price price) {
        if (price == null) {
            return null;
        }
        PriceDto dto = new PriceDto();
        dto.setPriceList(price.getPriceList());
        dto.setBrandId(price.getBrandId());
        dto.setProductId(price.getProductId());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPriority(price.getPriority());
        dto.setPrice(price.getPrice());
        dto.setCurr(price.getCurr());
        return dto;
    }

    public static PriceResponseDto toResponseDto(Price price) {
        if (price == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PriceResponseDto responseDto = new PriceResponseDto();
        responseDto.setPriceList(price.getPriceList());
        responseDto.setBrandId(price.getBrandId());
        responseDto.setStartDate(sdf.format(price.getStartDate()));
        responseDto.setEndDate(sdf.format(price.getEndDate()));
        responseDto.setPrice(price.getPrice());
        return responseDto;
    }
}
