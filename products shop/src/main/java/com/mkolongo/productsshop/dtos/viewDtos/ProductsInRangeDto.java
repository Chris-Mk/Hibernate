package com.mkolongo.productsshop.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductsInRangeDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String sellerFullName;
}
