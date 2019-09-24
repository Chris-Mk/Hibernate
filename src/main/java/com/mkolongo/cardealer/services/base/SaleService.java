package com.mkolongo.cardealer.services.base;

import com.mkolongo.cardealer.dtos.viewDtos.SaleDiscountViewDto;
import com.mkolongo.cardealer.models.Sale;

import java.util.List;

public interface SaleService {

    void seedSalesIntoDatabase();

    List<SaleDiscountViewDto> getSalesWithDiscount();
}
