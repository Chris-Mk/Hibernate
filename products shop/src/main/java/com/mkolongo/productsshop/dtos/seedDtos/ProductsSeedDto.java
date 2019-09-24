package com.mkolongo.productsshop.dtos.seedDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductsSeedDto {

    @Expose
    @Length(min = 3, message = "Product name must be at least 3 characters long!")
    private String name;

    @Expose
    private BigDecimal price;

    @Override
    public String toString() {
        return "ProductsSeedDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
