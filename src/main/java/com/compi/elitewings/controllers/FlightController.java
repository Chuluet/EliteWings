package com.compi.elitewings.controllers;

import com.compi.elitewings.models.Flight;
import com.compi.elitewings.services.implementations.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
@Tag(name = "Flights", description = "Endpoints para la gestión de vuelos")
public class FlightController {
    private final FlightService serviceFlight;

    @Autowired
    public FlightController(FlightService serviceFlight) {
        this.serviceFlight = serviceFlight;
    }

    @Operation(summary = "Obtener todos los vuelos", description = "Devuelve una lista de todos los vuelos registrados.")
    @GetMapping("/")
    public List<Flight> index() {
        return this.serviceFlight.getFlights();
    }

    @Operation(summary = "Agregar un nuevo vuelo", description = "Registra un nuevo vuelo en la base de datos.")
    @PostMapping("/")
    public Flight add(@RequestBody Flight flight) {
        this.serviceFlight.addFlight(flight);
        return flight;
    }

    @Operation(summary = "Buscar vuelo por ID", description = "Devuelve un vuelo específico según su ID único.")
    @GetMapping("/buscarId/{id}")
    public Flight buscar(@PathVariable UUID id) {
        return this.serviceFlight.getFlightById(id).orElse(null);
    }

    @Operation(summary = "Buscar vuelo por ID de celebridad", description = "Devuelve el vuelo asociado a una celebridad específica.")
    @GetMapping("/buscarVueloCelebridad/{celebrityId}")
    public Flight buscarVueloCelebridad(@PathVariable UUID celebrityId) {
        return this.serviceFlight.getFlightByCelebrityId(celebrityId).orElse(null);
    }

    @Operation(summary = "Buscar vuelo por ID de jet privado", description = "Devuelve el vuelo asociado a un jet privado específico.")
    @GetMapping("/buscarVueloJetPrivado/{privateJetId}")
    public Flight buscarvueloJetPrivado(@PathVariable UUID privateJetId) {
        return this.serviceFlight.getFlightByPrivateJetId(privateJetId).orElse(null);
    }

    @Operation(summary = "Buscar vuelos por aeropuerto de salida", description = "Devuelve una lista de vuelos que salen desde el aeropuerto especificado.")
    @GetMapping("/buscarVueloSalida/{departureAirPort}")
    public List<Flight> buscarVueloAereoPuertoSalida(@PathVariable String departureAirPort) {
        return this.serviceFlight.getFlightByDepartureAirports(departureAirPort);
    }

    @Operation(summary = "Buscar vuelos por aeropuerto de llegada", description = "Devuelve una lista de vuelos que llegan al aeropuerto especificado.")
    @GetMapping("/buscarVueloLlegada/{arrivalAirPort}")
    public List<Flight> buscarVueloAereoPuertoLlegada(@PathVariable String arrivalAirPort) {
        return this.serviceFlight.getFlightByArrivalAirports(arrivalAirPort);
    }

    @Operation(summary = "Buscar vuelos por hora de llegada", description = "Devuelve una lista de vuelos que llegan a la hora especificada.")
    @GetMapping("/buscarVueloHoraLlegada/{time}")
    public List<Flight> buscarVueloAereoPuertoHoraLLegada(@PathVariable Timestamp time) {
        return this.serviceFlight.getFlightByArrivalTime(time);
    }

    @Operation(summary = "Buscar vuelos por hora de salida", description = "Devuelve una lista de vuelos que salen a la hora especificada.")
    @GetMapping("/buscarVueloHoraSalida/{time}")
    public List<Flight> buscarVueloAereoPuertoHoraSalida(@PathVariable Timestamp time) {
        return this.serviceFlight.getFlightByDepartureTime(time);
    }

    @Operation(summary = "Actualizar información de un vuelo", description = "Modifica los datos de un vuelo existente.")
    @PutMapping("/update/{id}")
    public Flight update(@PathVariable UUID id, @RequestBody Flight flight) {
        this.serviceFlight.updateFlight(id, flight);
        return flight;
    }

    @Operation(summary = "Eliminar un vuelo", description = "Elimina un vuelo por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        this.serviceFlight.deleteFlight(id);
    }
}
