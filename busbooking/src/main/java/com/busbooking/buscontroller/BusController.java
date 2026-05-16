package com.busbooking.buscontroller;

import com.busbooking.bus.Bus;
import com.busbooking.busrepository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BusController {

    @Autowired
    BusRepository repository;

    // SAVE BUS
    @PostMapping("/saveBus")
    @ResponseBody
    public String saveBus(Bus bus) {

        repository.save(bus);

        return "Bus Added Successfully";
    }

    // VIEW ALL BUSES
    @GetMapping("/buses")
    public String getAllBuses(Model model) {

        model.addAttribute("buses",
                repository.findAll());

        return "buses";
    }

    // SEARCH BUS
    @GetMapping("/searchBus")
    @ResponseBody
    public Object searchBus(@RequestParam String source,
                            @RequestParam String destination) {

        return repository.findBySourceAndDestination(
                source,
                destination
        );
    }
}