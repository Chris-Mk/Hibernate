package com.mkolongo.cardealer.services.base;

import com.mkolongo.cardealer.dtos.seedDtos.PartSeedDto;

public interface PartService {

    void seedPartsIntoDatabase(PartSeedDto[] partSeedDtos);
}
