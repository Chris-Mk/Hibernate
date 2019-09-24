package com.mkolongo.productsshop.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CategoryViewDto {

    @Expose
    private String category;

    @Expose
    private int productCount;

    @Expose
    private BigDecimal averagePrice;

    @Expose
    private BigDecimal totalRevenue;
}
