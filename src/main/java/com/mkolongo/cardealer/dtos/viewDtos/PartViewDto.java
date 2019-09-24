package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PartViewDto {
    private String Name;
    private BigDecimal Price;

    @Override
    public String toString() {
        return "PartViewDto{" +
                "Name='" + Name + '\'' +
                ", Price=" + Price +
                '}';
    }
}
