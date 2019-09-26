package com.mkolongo.cardealer.controllers;

import com.mkolongo.cardealer.dtos.seedDtos.*;
import com.mkolongo.cardealer.dtos.viewDtos.*;
import com.mkolongo.cardealer.models.Supplier;
import com.mkolongo.cardealer.repositories.SupplierRepository;
import com.mkolongo.cardealer.services.base.*;
import com.mkolongo.cardealer.utils.base.FileUtil;
import com.mkolongo.cardealer.utils.base.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
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

    private void salesWithAppliedDiscount() throws JAXBException {
        final List<SaleDiscountViewDto> saleDiscountViewDtos = saleService.getSalesWithDiscount();

//        -------parse to json-------
//        final String jsonString = parser.toJsonString(saleDiscountViewDtos);
//        System.out.println(jsonString);

//        -------parse to xml-------
        SalesDiscountListViewDto salesDiscountListViewDto = new SalesDiscountListViewDto();
        salesDiscountListViewDto.setSales(saleDiscountViewDtos);
        parser.toXmlString(salesDiscountListViewDto);
    }

    private void totalSalesByCustomer() throws JAXBException {
        final Set<CustomerSalesViewDto> customerSalesViewDtos = customerService.getCustomersMoneySpent();

//        -------parse to json------
//        final String jsonString = parser.toJsonString(customerSalesViewDtos);
//        System.out.println(jsonString);

//        -------parse to xml-------
        CustomerSalesListViewDto customerSalesListViewDto = new CustomerSalesListViewDto();
        customerSalesListViewDto.setCustomers(customerSalesViewDtos);
        parser.toXmlString(customerSalesListViewDto);
    }

    private void carsWithTheirListOfParts() throws JAXBException {
        final List<CarPartViewDto> carPartViewDtos = carService.getAllCarsWithTheirParts();

//        -------parse to json--------
//        final String jsonString = parser.toJsonString(carPartViewDtos);
//        System.out.println(jsonString);

//        -------parse to xml---------
        CarPartListViewDto carPartListViewDto = new CarPartListViewDto();
        carPartListViewDto.setCars(carPartViewDtos);
        parser.toXmlString(carPartListViewDto);
    }

    private void localSuppliers() throws JAXBException {
        final List<SupplierViewDto> supplierViewDtos = supplierService.getAllLocalSuppliers();

//        -------parse to json--------
//        final String jsonString = parser.toJsonString(supplierViewDtos);
//        System.out.println(jsonString);

//        -------parse to xml--------
        SupplierListViewDto supplierListViewDto = new SupplierListViewDto();
        supplierListViewDto.setSuppliers(supplierViewDtos);
        parser.toXmlString(supplierListViewDto);
    }

    private void carsFromMakeToyota() throws JAXBException {
        final String carMake = scanner.nextLine();
        final List<CarViewDto> carViewDtos = carService.getAllCarsFromMake(carMake);

//        -------parse to json------
//        final String jsonString = parser.toJsonString(carViewDtos);
//        System.out.println(jsonString);

//        -------parse to xml--------
        CarListViewDto carListViewDto = new CarListViewDto();
        carListViewDto.setCars(carViewDtos);
        parser.toXmlString(carListViewDto);
    }

    private void orderedCustomer() throws JAXBException {
        final List<CustomerViewDto> customerViewDtos = customerService.getAllCustomersOrderByBirthDate();

//        -----parse to json-----
//        final String jsonString = parser.toJsonString(customerViewDtos);
//        System.out.println(jsonString);

//        ------parse to xml------
        CustomerListViewDto customerListSeedDto = new CustomerListViewDto();
        customerListSeedDto.setCustomers(customerViewDtos);
        parser.toXmlString(customerListSeedDto);
    }

    private void seedSales() {
        saleService.seedSalesIntoDatabase();
    }

    private void seedCustomers() throws IOException, JAXBException {
//        -------seed from json-------
//        final String customerInfo = fileUtil.readFile("files/customers.json");
//        final CustomerSeedDto[] customerSeedDtos = parser.fromJsonString(customerInfo, CustomerSeedDto[].class);
//        customerService.seedCustomersIntoDatabase(customerSeedDtos);

//        --------seed from xml-------
        final String customersInfo = fileUtil.readFile("files/customers.xml");
        final CustomerListSeedDto customerListSeedDto = parser.fromXmlString(customersInfo, CustomerListSeedDto.class);
        customerService.seedCustomersIntoDatabase(customerListSeedDto.getCustomers().toArray(CustomerSeedDto[]::new));
    }

    private void seedCars() throws IOException, JAXBException {
//        -------seed from json-------
//        final String carsInfo = fileUtil.readFile("files/cars.json");
//        final CarSeedDto[] carSeedDtos = parser.fromJsonString(carsInfo, CarSeedDto[].class);
//        carService.seedCarsIntoDatabase(carSeedDtos);

//        --------seed from xml-------
        final String carsInfo = fileUtil.readFile("files/cars.xml");
        final CarListSeedDto carListSeedDto = parser.fromXmlString(carsInfo, CarListSeedDto.class);
        carService.seedCarsIntoDatabase(carListSeedDto.getCars().toArray(CarSeedDto[]::new));
    }

    private void seedParts() throws IOException, JAXBException {
//        -------seed from json-------
//        final String partsInfo = fileUtil.readFile("files/parts.json");
//        final PartSeedDto[] partSeedDtos = parser.fromJsonString(partsInfo, PartSeedDto[].class);
//        partService.seedPartsIntoDatabase(partSeedDtos);

//        --------seed from xml-------
        final String partsInfo = fileUtil.readFile("files/parts.xml");
        final PartListSeedDto partListSeedDto = parser.fromXmlString(partsInfo, PartListSeedDto.class);
        partService.seedPartsIntoDatabase(partListSeedDto.getParts().toArray(PartSeedDto[]::new));
    }

    private void seedSuppliers() throws IOException, JAXBException {
//        -------seed from json-------
//        final String suppliersInfo = fileUtil.readFile("files/suppliers.json");
//        final SupplierSeedDto[] supplierSeedDtos = parser.fromJsonString(suppliersInfo, SupplierSeedDto[].class);
//        supplierService.seedSuppliersIntoDatabase(supplierSeedDtos);

//        --------seed from xml-------
        final String suppliersInfo = fileUtil.readFile("files/suppliers.xml");
        final SupplierListSeedDto supplierListSeedDto = parser.fromXmlString(suppliersInfo, SupplierListSeedDto.class);
        supplierService.seedSuppliersIntoDatabase(supplierListSeedDto.getSuppliers().toArray(SupplierSeedDto[]::new));
    }
}
