package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
public class CustomerSalesViewDto {
    private String fullName;
    private int boughtCars;
    private BigDecimal moneySpent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSalesViewDto that = (CustomerSalesViewDto) o;
        return boughtCars == that.boughtCars &&
                fullName.equals(that.fullName) &&
                moneySpent.equals(that.moneySpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, boughtCars, moneySpent);
    }
}
