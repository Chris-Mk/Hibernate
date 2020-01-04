package com.mkolongo.cardealer.services;

import com.mkolongo.cardealer.dtos.seedDtos.CustomerSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.CustomerSalesViewDto;
import com.mkolongo.cardealer.dtos.viewDtos.CustomerViewDto;
import com.mkolongo.cardealer.models.Car;
import com.mkolongo.cardealer.models.Customer;
import com.mkolongo.cardealer.models.Part;
import com.mkolongo.cardealer.models.Sale;
import com.mkolongo.cardealer.repositories.CustomerRepository;
import com.mkolongo.cardealer.services.base.CustomerService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Validator validator) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void seedCustomersIntoDatabase(CustomerSeedDto[] customerSeedDtos) {
        final Set<ConstraintViolation<CustomerSeedDto[]>> violations = validator.validate(customerSeedDtos);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<CustomerSeedDto[]> violation : violations) {
                System.out.println(violation.getMessage());
            }
            return;
        }

        if (customerRepository.count() == 0) {
            Converter<String, LocalDateTime> getCustomerBirthDate = new AbstractConverter<>() {
                @Override
                protected LocalDateTime convert(String source) {
                    return LocalDateTime.parse(source, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
            };

            modelMapper.createTypeMap(CustomerSeedDto.class, Customer.class)
                    .addMappings(mapping -> mapping.using(getCustomerBirthDate)
                            .map(CustomerSeedDto::getBirthDate,
                                    (destination, value) -> destination.setBirthDate((LocalDateTime) value)));

            final Customer[] customers = modelMapper.map(customerSeedDtos, Customer[].class);

            customerRepository.saveAll(Arrays.asList(customers));
        }
    }

    @Override
    public List<CustomerViewDto> getAllCustomersOrderByBirthDate() {
        List<CustomerViewDto> customerViewDtos = new ArrayList<>();

//        modelMapper.createTypeMap(Customer.class, CustomerViewDto.class)
//                .addMappings(mapping -> mapping.skip(CustomerViewDto::setSales));

        customerRepository.findAll()
                .stream()
                .sorted((c1, c2) -> {
                    int sort = c1.getBirthDate().compareTo(c2.getBirthDate());

                    if (sort == 0) {
                        sort = Boolean.compare(c1.getIsYoungDriver(), c2.getIsYoungDriver());
                    }
                    return sort;
                })
                .forEach(customer -> {
                    final CustomerViewDto customerViewDto = modelMapper.map(customer, CustomerViewDto.class);
                    customerViewDto.setSales(new HashSet<>());
                    customerViewDtos.add(customerViewDto);
                });
        return customerViewDtos;
    }

    @Override
    public Set<CustomerSalesViewDto> getCustomersMoneySpent() {
        modelMapper.createTypeMap(Customer.class, CustomerSalesViewDto.class)
                .addMappings(mapping -> mapping
                        .map(Customer::getName,
                                (destination, value) -> destination.setFullName((String) value)));

        Set<CustomerSalesViewDto> customerSalesViewDtos = new LinkedHashSet<>();

        customerRepository.findCustomersBySalesNotNull()
                .stream()
                .sorted((c1, c2) -> {
                    final BigDecimal c1TotalPrice = getTotalCarsPrice(c1);
                    final BigDecimal c2TotalPrice = getTotalCarsPrice(c2);

                    int sort = c2TotalPrice.compareTo(c1TotalPrice);

                    if (sort == 0) {
                        final long c1CarCount = getCarsCount(c1);
                        final long c2CarCount = getCarsCount(c2);

                        sort = Long.compare(c2CarCount, c1CarCount);
                    }
                    return sort;
                })
                .forEach(customer -> {
                    final CustomerSalesViewDto customerSalesViewDto = modelMapper.map(customer, CustomerSalesViewDto.class);
                    customerSalesViewDto.setBoughtCars((int) getCarsCount(customer));
                    customerSalesViewDto.setMoneySpent(getTotalCarsPrice(customer));

                    customerSalesViewDtos.add(customerSalesViewDto);
                });

        return customerSalesViewDtos;
    }

    private long getCarsCount(Customer customer) {
        return customer.getSales().stream()
                .map(Sale::getCar)
                .count();
    }

    private BigDecimal getTotalCarsPrice(Customer customer) {
        return customer.getSales().stream()
                .map(Sale::getCar)
                .map(Car::getParts)
                .map(parts -> {
                    BigDecimal totalPrice = new BigDecimal("0.00");

                    for (Part part : parts) {
                        totalPrice = totalPrice.add(part.getPrice());
                    }
                    return totalPrice;
                })
                .findFirst()
                .get();
    }
}
