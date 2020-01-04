package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSalesViewDto {

    @XmlAttribute
    private String fullName;

    @XmlAttribute(name = "bought-cars")
    private int boughtCars;

    @XmlAttribute(name = "spent-money")
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
