package com.alper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by Alper on 26.04.2018.
 */
@Entity
@Data
@AllArgsConstructor
public class Bus extends  Car {
    private int capacity;

    public Bus(String model, String plaka, String driverName, int modelYear, int capacity) {
        super(model, plaka, driverName, modelYear);
        this.capacity = capacity;
    }

    public Bus() {}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
