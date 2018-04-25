package com.alper.service;

import com.alper.domain.Car;
import com.alper.repository.CarRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Alper on 25.04.2018.
 */
@Service
public class CarServiceImp implements CarService {

    private CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Iterable<Car> list() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }
}
