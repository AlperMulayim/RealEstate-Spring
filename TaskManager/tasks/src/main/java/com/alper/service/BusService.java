package com.alper.service;

import com.alper.domain.Bus;

/**
 * Created by Alper on 26.04.2018.
 */
public interface BusService {
    Bus save(Bus bus);
    Iterable<Bus> list();
}
