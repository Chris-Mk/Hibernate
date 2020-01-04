package com.mkolongo.cardealer.services;

import com.mkolongo.cardealer.dtos.viewDtos.CarViewDto;
import com.mkolongo.cardealer.dtos.viewDtos.SaleDiscountViewDto;
import com.mkolongo.cardealer.models.*;
import com.mkolongo.cardealer.repositories.CarRepository;
import com.mkolongo.cardealer.repositories.CustomerRepository;
import com.mkolongo.cardealer.repositories.SaleRepository;
import com.mkolongo.cardealer.services.base.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleServiceImpl implements SaleService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository,
                           CarRepository carRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSalesIntoDatabase() {
        List<Sale> sales = new ArrayList<>();

        final List<Car> cars = carRepository.findAll();
        final List<Customer> customers = customerRepository.findAll();
        Discount[] discounts = Discount.values();

        for (int i = 0; i < 30; i++) {
            final Customer currentCustomer = customers.get(i);

            for (int j = 0; j < 11; j++) {
                final Car randomCar = getRandomCar(cars);
                int randomDiscount = getRandomDiscount(discounts);

                if (currentCustomer.getIsYoungDriver()) {
                    randomDiscount += Discount.FIVE.getDiscount();
                }

                sales.add(new Sale(randomCar, currentCustomer, randomDiscount + "%"));
            }
        }

        saleRepository.saveAll(sales);
    }

    @Override
    @Transactional
    public List<SaleDiscountViewDto> getSalesWithDiscount() {
        modelMapper.createTypeMap(Car.class, CarViewDto.class)
                .addMappings(mapping -> mapping.skip(CarViewDto::setId));

        List<SaleDiscountViewDto> saleDiscountViewDtos = new ArrayList<>();

        saleRepository.findAll()
                .forEach(sale -> {
                    final Set<Part> parts = sale.getCar().getParts();
                    BigDecimal totalPrice = new BigDecimal("0.00");

                    for (Part part : parts) {
                        totalPrice = totalPrice.add(part.getPrice());
                    }

                    final double discount = Double.parseDouble(sale.getDiscountPercentage()
                            .substring(0, sale.getDiscountPercentage().indexOf("%"))) / 100.0;

                    BigDecimal priceWithDiscount = totalPrice.multiply(new BigDecimal(discount));
                    final CarViewDto carViewDto = modelMapper.map(sale.getCar(), CarViewDto.class);

                    SaleDiscountViewDto saleDiscountViewDto = new SaleDiscountViewDto();
                    saleDiscountViewDto.setCar(carViewDto);
                    saleDiscountViewDto.setCustomerName(sale.getCustomer().getName());
                    saleDiscountViewDto.setDiscount(discount);
                    saleDiscountViewDto.setPrice(totalPrice);
                    saleDiscountViewDto.setPriceWithDiscount(priceWithDiscount);

                    saleDiscountViewDtos.add(saleDiscountViewDto);
                });

        return saleDiscountViewDtos;
    }

    private int getRandomDiscount(Discount[] discounts) {
        Collections.shuffle(Arrays.asList(discounts));
        return discounts[0].getDiscount();
    }

    private Car getRandomCar(List<Car> cars) {
        Collections.shuffle(cars);
        return cars.get(0);
    }
}
