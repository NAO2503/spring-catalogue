package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.usecase.GetPriceUseCase;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceRequestDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper.PriceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/catalogue")
public class CatalogueRestController {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping("/price/{priceList}")
    public ResponseEntity<PriceResponseDto> getPriceByPriceList(@PathVariable Long priceList) {

        return new ResponseEntity<>(PriceMapper.toResponseDto(getPriceUseCase.getPrice(priceList)), HttpStatus.OK);

    }

    @PostMapping("/price/findByBrandProductBetweenDate")
    public ResponseEntity<PriceResponseDto> findByBrandProductBetweenDate(@Valid @RequestBody PriceRequestDto priceRequestDto) {

        return new ResponseEntity<>(PriceMapper.toResponseDto(getPriceUseCase.findByBrandProductBetweenDate(priceRequestDto.getBrandId(),
                priceRequestDto.getProductId(), priceRequestDto.getDateQuery())), HttpStatus.OK);

    }

    @PostMapping("/price/listAllByBrandProductBetweenDate")
    public ResponseEntity<List<PriceDto>> listAllByBrandProductBetweenDate(@Valid @RequestBody PriceRequestDto priceRequestDto) {

        List<Price> result = getPriceUseCase.listAllByBrandProductBetweenDate(priceRequestDto.getBrandId(),
                priceRequestDto.getProductId(), priceRequestDto.getDateQuery());

        return new ResponseEntity<>(PriceMapper.listToDtoList(result), HttpStatus.OK);

    }
}
