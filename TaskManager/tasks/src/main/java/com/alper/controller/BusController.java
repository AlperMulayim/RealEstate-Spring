package com.alper.controller;

import com.alper.domain.Bus;
import com.alper.service.BusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alper on 26.04.2018.
 */
@RestController
@RequestMapping("/api/buses")
public class BusController {
    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @RequestMapping("")
    public Iterable<Bus> listBuses(){
        return busService.list();
    }

    @RequestMapping("/totalcapacity")
    public String totalCapacity(){
        int capacity =0;
        for(Bus bus : busService.list()){
            capacity +=bus.getCapacity();
        }
        return "There is ( " + capacity +  " ) capacity now";
    }
}
