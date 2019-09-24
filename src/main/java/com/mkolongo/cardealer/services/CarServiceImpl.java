package com.mkolongo.cardealer.services;

import com.mkolongo.cardealer.dtos.seedDtos.CarSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.CarPartViewDto;
import com.mkolongo.cardealer.dtos.viewDtos.CarViewDto;
import com.mkolongo.cardealer.dtos.viewDtos.PartViewDto;
import com.mkolongo.cardealer.models.Car;
import com.mkolongo.cardealer.models.Part;
import com.mkolongo.cardealer.repositories.CarRepository;
import com.mkolongo.cardealer.repositories.PartRepository;
import com.mkolongo.cardealer.services.base.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private static Random random = new Random();

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CarServiceImpl(PartRepository partRepository, CarRepository carRepository,
                          ModelMapper modelMapper, Validator validator) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void seedCarsIntoDatabase(CarSeedDto[] carSeedDtos) {
        final Set<ConstraintViolation<CarSeedDto[]>> violations = validator.validate(carSeedDtos);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<CarSeedDto[]> violation : violations) {
                System.out.println(violation.getMessage());
            }
            return;
        }

        if (carRepository.count() == 0) {
            final Car[] cars = modelMapper.map(carSeedDtos, Car[].class);
            final List<Part> parts = partRepository.findAll();

            for (Car car : cars) {
                final Set<Part> randomParts = getRandomParts(parts);
                car.setParts(randomParts);
            }

            carRepository.saveAll(Arrays.asList(cars));
        }
    }

    @Override
    public List<CarViewDto> getAllCarsFromMake(String make) {
        List<CarViewDto> carViewDtos = new ArrayList<>();

        carRepository.findCarsByMake(make)
                .stream()
                .sorted((c1, c2) -> {
                    int sort = c1.getModel().compareTo(c2.getModel());

                    if (sort == 0) {
                        sort = Long.compare(c2.getTravelledDistance(), c1.getTravelledDistance());
                    }
                    return sort;
                })
                .forEach(car -> {
                    final CarViewDto carViewDto = modelMapper.map(car, CarViewDto.class);
                    carViewDtos.add(carViewDto);
                });

        return carViewDtos;
    }

    @Override
    @Transactional
    public List<CarPartViewDto> getAllCarsWithTheirParts() {
        modelMapper.createTypeMap(Car.class, CarViewDto.class)
                .addMappings(mapping -> mapping.skip(CarViewDto::setId));

        final List<Car> cars = carRepository.findAll();
        List<CarPartViewDto> carPartViewDtos = new ArrayList<>();

        for (Car car : cars) {
            final Set<Part> parts = car.getParts();

            final Set<PartViewDto> partViewDtos = modelMapper.map(parts, new TypeToken<Set<PartViewDto>>() {}.getType());
            final CarViewDto carViewDto = modelMapper.map(car, CarViewDto.class);

            CarPartViewDto carPartViewDto = new CarPartViewDto(carViewDto, partViewDtos);
            carPartViewDtos.add(carPartViewDto);
        }

        return carPartViewDtos;
    }

    private Set<Part> getRandomParts(List<Part> parts) {
        final int randomPartsQuantity = random.nextInt(21 - 10) + 10;

        Set<Part> randomParts = new LinkedHashSet<>();

        for (int i = 0; i < randomPartsQuantity; i++) {
            Collections.shuffle(parts);
            Part part = parts.get(0);

            while (randomParts.contains(part)) {
                Collections.shuffle(parts);
                part = parts.get(0);
            }

            randomParts.add(part);
        }

        return randomParts;
    }
}
