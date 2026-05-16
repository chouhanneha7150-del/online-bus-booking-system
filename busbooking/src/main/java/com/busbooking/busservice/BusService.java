package com.busbooking.busservice;

import com.busbooking.bus.Bus;
import com.busbooking.busrepository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository repository;

    // SAVE BUS
    public Bus saveBus(Bus bus) {

        return repository.save(bus);
    }

    // GET ALL BUSES
    public List<Bus> getAllBuses() {

        return repository.findAll();
    }

    // SEARCH BUS
    public List<Bus> searchBus(String source,
                               String destination) {

        return repository.findBySourceAndDestination(source,
                destination);
    }
}