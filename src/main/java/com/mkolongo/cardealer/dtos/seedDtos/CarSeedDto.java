package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarSeedDto {

    @NotNull(message = "Car make cannot be empty!")
    private String make;

    @NotNull(message = "Car model cannot be empty!")
    private String model;

    @NotNull(message = "Car travelled distance cannot be empty!")
    private Long travelledDistance;
}
