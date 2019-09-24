package com.mkolongo.cardealer.repositories;

import com.mkolongo.cardealer.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByMake(String make);
}
