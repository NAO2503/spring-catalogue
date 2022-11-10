package com.catalogue.cleanarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private Long brandId;

    private Date startDate;

    private Date endDate;

    private Long priceList;

    private Long productId;

    private Integer priority;

    private Double price;

    private String curr;
}
