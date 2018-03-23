package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Alper on 23.03.2018.
 */
@Entity
public class House {
    @Id
    @GeneratedValue
    private long id;
    private String address;

    public House(long id, String address) {
        this.id = id;
        this.address = address;
    }
    public House(String address){
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
