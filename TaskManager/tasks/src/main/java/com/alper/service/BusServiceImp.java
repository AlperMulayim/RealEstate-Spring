package com.alper.service;

import com.alper.domain.Bus;
import com.alper.repository.BusRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Alper on 26.04.2018.
 */
@Service
public class BusServiceImp implements BusService{
    BusRepository busRepo;

    public BusServiceImp(BusRepository busRepo) {
        this.busRepo = busRepo;
    }


    @Override
    public Bus save(Bus bus) {
        return busRepo.save(bus);
    }

    @Override
    public Iterable<Bus> list() {
        return busRepo.findAll();
    }
}
