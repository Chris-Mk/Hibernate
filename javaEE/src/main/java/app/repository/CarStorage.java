package app.repository;

import app.domain.entities.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CarStorage {
    private static final List<Car> cars = new ArrayList<>();

    public static void addCar(Car car) {
        cars.add(car);
    }

    public static List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
