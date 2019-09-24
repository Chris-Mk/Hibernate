package com.mkolongo.productsshop.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private ProductInfoDto soldProducts;
}
