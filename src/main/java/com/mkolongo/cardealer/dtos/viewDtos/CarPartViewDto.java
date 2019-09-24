package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class CarPartViewDto {
    private CarViewDto car;
    private Set<PartViewDto> parts;
}
