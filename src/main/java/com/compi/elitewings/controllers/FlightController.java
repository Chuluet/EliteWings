package com.compi.elitewings.controllers;


import com.compi.elitewings.models.Flight;
import com.compi.elitewings.services.implementations.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService serviceFlight;
    @Autowired
    public FlightController(FlightService serviceFlight) {
        this.serviceFlight = serviceFlight;
    }

    @GetMapping("/")
    public List<Flight> index() {
        return this.serviceFlight.getFlights();
    }

    @PostMapping("/")
    public Flight add(@RequestBody Flight Flight) {
        this.serviceFlight.addFlight(Flight);
        return Flight;
    }

    @GetMapping("/buscarId/{id}")
    public Flight buscar(@PathVariable UUID id) {
        return this.serviceFlight.getFlightById(id)
                .orElse(null);
    }

    @GetMapping("/buscarVueloCelebridad/{celebrityId}")
    public Flight buscarVueloCelebridad(@PathVariable UUID celebrityId) {
        return this.serviceFlight.getFlightByCelebrityId(celebrityId).orElse(null);
    }

    @GetMapping("/buscarVueloJetPrivado/{privateJetId}")
    public Flight buscarvueloJetPrivado(@PathVariable UUID privateJetId) {
        return this.serviceFlight.getFlightByPrivateJetId(privateJetId).orElse(null);
    }
    @GetMapping("/buscarVueloSalida/{departureAirPort}")
    public List<Flight>  buscarVueloAereoPuertoSalida(@PathVariable String departureAirPort) {
        return this.serviceFlight.getFlightByDepartureAirports(departureAirPort);
    }
    @GetMapping("/buscarVueloLlegada/{arrivalAirPort}")
    public List<Flight> buscarVueloAereoPuertoLlegada(@PathVariable String arrivalAirPort) {
        return this.serviceFlight.getFlightByArrivalAirports(arrivalAirPort);
    }
    @GetMapping("/buscarVueloHoraLlegada/{time}")
    public List<Flight> buscarVueloAereoPuertoHoraLLegada(@PathVariable Timestamp time) {
        return this.serviceFlight.getFlightByArrivalTime(time);
    }
    @GetMapping("/buscarVueloHoraSalida/{time}")
    public List<Flight> buscarVueloAereoPuertoHoraSalida(@PathVariable Timestamp time) {
        return this.serviceFlight.getFlightByDepartureTime(time);
    }
}
