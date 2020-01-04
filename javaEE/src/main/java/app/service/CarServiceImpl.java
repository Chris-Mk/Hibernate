package app.service;

import app.domain.entities.Car;
import app.domain.models.service.CarServiceModel;
import app.repository.CarStorage;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;

    public CarServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCar(CarServiceModel carServiceModel) {
        CarStorage.addCar(this.modelMapper.map(carServiceModel, Car.class));
    }

    @Override
    public List<CarServiceModel> allCars() {
        return this.modelMapper.map(CarStorage.getCars(), new TypeToken<List<CarServiceModel>>() {}.getType());
    }
}
