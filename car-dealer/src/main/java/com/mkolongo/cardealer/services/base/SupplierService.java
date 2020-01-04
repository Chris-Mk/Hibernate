package com.mkolongo.cardealer.services.base;

import com.mkolongo.cardealer.dtos.seedDtos.SupplierSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.SupplierViewDto;

import java.util.List;

public interface SupplierService {

    void seedSuppliersIntoDatabase(SupplierSeedDto[] supplierSeedDtos);

    List<SupplierViewDto> getAllLocalSuppliers();

}
