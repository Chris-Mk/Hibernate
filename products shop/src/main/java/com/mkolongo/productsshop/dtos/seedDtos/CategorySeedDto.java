package com.mkolongo.productsshop.dtos.seedDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class CategorySeedDto {

    @Expose
    @Length(min = 3, max = 15, message = "Category name must be between 3 and 15 characters long!")
    private String name;

    @Override
    public String toString() {
        return "CategorySeedDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
