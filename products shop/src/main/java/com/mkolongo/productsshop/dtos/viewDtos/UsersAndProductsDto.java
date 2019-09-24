package com.mkolongo.productsshop.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersAndProductsDto {

    @Expose
    private int usersCount;

    @Expose
    private List<UserDto> users;
}
