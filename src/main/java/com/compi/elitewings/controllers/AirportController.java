package com.compi.elitewings.controllers;

import com.compi.elitewings.models.Airport;
import com.compi.elitewings.services.IServiceAirport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private final IServiceAirport serviceAirport;

    @Autowired
    public AirportController(IServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    @GetMapping("/")
    public List<Airport> index() {
        return this.serviceAirport.getAirport();
    }

    @PostMapping("/")
    public Airport add(@RequestBody Airport airport) {
        this.serviceAirport.addAirport(airport);
        return airport;
    }

    @GetMapping("/buscarId/{id}")
    public Airport buscar(@PathVariable UUID id) {
        return this.serviceAirport.getAirportById(id)
                .orElse(null);
    }

    @GetMapping("/buscarLugar/{lugar}")
    public List<Airport> buscarLugar(@PathVariable String lugar) {
        return this.serviceAirport.getAirportByLocation(lugar);
    }

    @GetMapping("/buscarCapacidad/{capacidad}")
    public List<Airport> buscarCapacidad(@PathVariable int capacidad) {
        return this.serviceAirport.getAirportByCapacity(capacidad);
    }
    @GetMapping("/buscarOwners/{owners}")
    public Airport buscarOwners(@PathVariable String owners) {
        return this.serviceAirport.getAirportByOwners(owners).orElse(null);
    }
}
