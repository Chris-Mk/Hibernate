package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class PartSeedDto {

    @NotNull(message = "Part name cannot be empty!")
    private String name;

    @NotNull(message = "Part price cannot be empty!")
    @Digits(integer = 10, fraction = 2, message = "Invalid price!")
    private BigDecimal price;

    @NotNull(message = "Parts quantity cannot be empty!")
    private int quantity;
}
