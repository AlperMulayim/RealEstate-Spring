package com.alper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Alper on 25.04.2018.
 */
@Entity
@Data
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue
    private long id;
    private String model;
    private String plaka;
    private String driverName;
    private int modelYear;

    public Car(String model, String plaka, String driverName, int modelYear) {
        this.model = model;
        this.plaka = plaka;
        this.driverName = driverName;
        this.modelYear = modelYear;
    }
    public Car(){};
}
