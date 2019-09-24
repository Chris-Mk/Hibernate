package com.mkolongo.productsshop.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SoldProductsDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<ProductViewDto> soldProducts;
}
