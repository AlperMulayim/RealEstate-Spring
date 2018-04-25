package com.alper.repository;

import com.alper.domain.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alper on 25.04.2018.
 */
public interface CarRepository extends CrudRepository<Car,Long> {}
