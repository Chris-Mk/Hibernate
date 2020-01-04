package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedDto {

    @XmlAttribute
    @NotNull(message = "Part name cannot be empty!")
    private String name;

    @XmlAttribute
    @NotNull(message = "Part price cannot be empty!")
    @Digits(integer = 10, fraction = 2, message = "Invalid price!")
    private BigDecimal price;

    @XmlAttribute
    @NotNull(message = "Parts quantity cannot be empty!")
    private int quantity;
}
