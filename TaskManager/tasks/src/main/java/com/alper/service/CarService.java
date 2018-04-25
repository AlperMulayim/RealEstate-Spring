package com.alper.service;

import com.alper.domain.Car;

/**
 * Created by Alper on 25.04.2018.
 */
public interface CarService {
    Iterable<Car> list();
    Car save(Car car);
}
