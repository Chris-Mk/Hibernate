package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerSeedDto {

    @NotNull(message = "Customer name cannot be empty!")
    private String name;

    @NotNull(message = "Customer birth date cannot be empty!")
    private String birthDate;

    @NotNull(message = "Invalid field!")
    private Boolean isYoungDriver;
}
