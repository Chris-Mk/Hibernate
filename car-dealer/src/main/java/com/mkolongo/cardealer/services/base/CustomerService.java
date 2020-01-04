package com.mkolongo.cardealer.services.base;

import com.mkolongo.cardealer.dtos.seedDtos.CustomerSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.CustomerSalesViewDto;
import com.mkolongo.cardealer.dtos.viewDtos.CustomerViewDto;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    void seedCustomersIntoDatabase(CustomerSeedDto[] customerSeedDtos);

    List<CustomerViewDto> getAllCustomersOrderByBirthDate();

    Set<CustomerSalesViewDto> getCustomersMoneySpent();
}

