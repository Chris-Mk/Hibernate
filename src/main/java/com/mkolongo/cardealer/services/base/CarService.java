package com.mkolongo.cardealer.services.base;

import com.mkolongo.cardealer.dtos.seedDtos.CarSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.CarPartViewDto;
import com.mkolongo.cardealer.dtos.viewDtos.CarViewDto;

import java.util.List;

public interface CarService {

    void seedCarsIntoDatabase(CarSeedDto[] carSeedDtos);

    List<CarViewDto> getAllCarsFromMake(String make);

    List<CarPartViewDto> getAllCarsWithTheirParts();
}
