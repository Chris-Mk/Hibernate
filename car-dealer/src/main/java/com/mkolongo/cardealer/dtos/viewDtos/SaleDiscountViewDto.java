package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDiscountViewDto {

    private CarViewDto car;

    @XmlElement(name = "customer-name")
    private String CustomerName;

    @XmlElement(name = "discount")
    private Double Discount;

    @XmlElement(name = "price")
    private BigDecimal Price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal PriceWithDiscount;
}
