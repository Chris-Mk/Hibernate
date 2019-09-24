package com.mkolongo.cardealer.controllers;

import com.mkolongo.cardealer.dtos.seedDtos.CarSeedDto;
import com.mkolongo.cardealer.dtos.seedDtos.CustomerSeedDto;
import com.mkolongo.cardealer.dtos.seedDtos.PartSeedDto;
import com.mkolongo.cardealer.dtos.seedDtos.SupplierSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.*;
import com.mkolongo.cardealer.services.base.*;
import com.mkolongo.cardealer.utils.base.FileUtil;
import com.mkolongo.cardealer.utils.base.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final PartService partService;
    private final CarService carService;
    private final FileUtil fileUtil;
    private final Scanner scanner;
    private final Parser parser;

    @Autowired
    public ConsoleRunner(CustomerService customerService, SupplierService supplierService, SaleService saleService,
                         PartService partService, CarService carService, FileUtil fileUtil, Scanner scanner, Parser parser) {
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.partService = partService;
        this.carService = carService;
        this.fileUtil = fileUtil;
        this.scanner = scanner;
        this.parser = parser;
    }

    @Override
    public void run(String... args) throws Exception {
//        -------importing data into the database------
//        seedSuppliers();
//        seedParts();
//        seedCars();
//        seedCustomers();
//        seedSales();

//        --------querying the database---------
//        orderedCustomer();
//        carsFromMakeToyota();
//        localSuppliers();
//        carsWithTheirListOfParts();
//        totalSalesByCustomer();
        salesWithAppliedDiscount();
    }

    private void salesWithAppliedDiscount() {
        final List<SaleDiscountViewDto> saleDiscountViewDtos = saleService.getSalesWithDiscount();

        final String jsonString = parser.toJsonString(saleDiscountViewDtos);
        System.out.println(jsonString);
    }

    private void totalSalesByCustomer() {
        final Set<CustomerSalesViewDto> customerSalesViewDtos = customerService.getCustomersMoneySpent();

        final String jsonString = parser.toJsonString(customerSalesViewDtos);
        System.out.println(jsonString);
    }

    private void carsWithTheirListOfParts() {
        final List<CarPartViewDto> carPartViewDtos = carService.getAllCarsWithTheirParts();

        final String jsonString = parser.toJsonString(carPartViewDtos);
        System.out.println(jsonString);
    }

    private void localSuppliers() {
        final List<SupplierViewDto> supplierViewDtos = supplierService.getAllLocalSuppliers();

        final String jsonString = parser.toJsonString(supplierViewDtos);
        System.out.println(jsonString);
    }

    private void carsFromMakeToyota() {
        final String carMake = scanner.nextLine();

        final List<CarViewDto> carViewDtos = carService.getAllCarsFromMake(carMake);

        final String jsonString = parser.toJsonString(carViewDtos);
        System.out.println(jsonString);
    }

    private void orderedCustomer() {
        final List<CustomerViewDto> customerViewDtos = customerService.getAllCustomersOrderByBirthDate();

        final String jsonString = parser.toJsonString(customerViewDtos);
        System.out.println(jsonString);
    }

    private void seedSales() {
        saleService.seedSalesIntoDatabase();
    }

    private void seedCustomers() throws IOException {
        final String customerInfo = fileUtil.readFile("files/customers.json");

        final CustomerSeedDto[] customerSeedDtos = parser.toObject(customerInfo, CustomerSeedDto[].class);
        customerService.seedCustomersIntoDatabase(customerSeedDtos);
    }

    private void seedCars() throws IOException {
        final String carsInfo = fileUtil.readFile("files/cars.json");

        final CarSeedDto[] carSeedDtos = parser.toObject(carsInfo, CarSeedDto[].class);
        carService.seedCarsIntoDatabase(carSeedDtos);
    }

    private void seedParts() throws IOException {
        final String partsInfo = fileUtil.readFile("files/parts.json");

        final PartSeedDto[] partSeedDtos = parser.toObject(partsInfo, PartSeedDto[].class);
        partService.seedPartsIntoDatabase(partSeedDtos);
    }

    private void seedSuppliers() throws IOException {
        final String suppliersInfo = fileUtil.readFile("files/suppliers.json");

        final SupplierSeedDto[] supplierSeedDtos = parser.toObject(suppliersInfo, SupplierSeedDto[].class);
        supplierService.seedSuppliersIntoDatabase(supplierSeedDtos);
    }
}
