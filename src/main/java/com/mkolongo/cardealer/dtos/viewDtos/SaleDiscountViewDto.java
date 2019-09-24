package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SaleDiscountViewDto {
    private CarViewDto car;
    private String CustomerName;
    private Double Discount;
    private BigDecimal Price;
    private BigDecimal PriceWithDiscount;
}
