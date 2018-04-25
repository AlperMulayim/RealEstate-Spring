package com.alper.controller;

import com.alper.domain.Car;
import com.alper.service.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alper on 25.04.2018.
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping("")
    public Iterable<Car> listCars(){
        return  carService.list();
    }
}
