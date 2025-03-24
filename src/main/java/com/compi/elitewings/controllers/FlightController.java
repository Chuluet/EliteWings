package com.compi.elitewings.controllers;

import com.compi.elitewings.exception.ApiRequestException;
import com.compi.elitewings.models.Flight;
import com.compi.elitewings.services.implementations.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
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
        List<Flight> flights = serviceFlight.getFlights();
        if (flights.isEmpty()) {
            throw new ApiRequestException("No hay vuelos registrados.");
        }
        return flights;
    }

    @Operation(summary = "Agregar un nuevo vuelo", description = "Registra un nuevo vuelo en la base de datos.")
    @PostMapping("/")
    public Flight add(@Valid @RequestBody Flight flight) {
        try {
            this.serviceFlight.addFlight(flight);
            return flight;
        } catch (Exception e) {
            throw new ApiRequestException("Error al agregar el vuelo: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar vuelo por ID", description = "Devuelve un vuelo específico según su ID único.")
    @GetMapping("/buscarId/{id}")
    public Flight buscar(@PathVariable UUID id) {
        return this.serviceFlight.getFlightById(id).orElseThrow(() -> new ApiRequestException("No se encontró el vuelo con ID: " + id));
    }

    @Operation(summary = "Buscar vuelo por ID de celebridad", description = "Devuelve el vuelo asociado a una celebridad específica.")
    @GetMapping("/buscarVueloCelebridad/{celebrityId}")
    public List<Flight> buscarVueloCelebridad(@PathVariable UUID celebrityId) {
        List<Flight> flights = this.serviceFlight.getFlightByCelebrityId(celebrityId);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encuentran vuelos con el celebrity Id.");
        }
        return flights;
    }

    @Operation(summary = "Buscar vuelo por ID de jet privado", description = "Devuelve el vuelo asociado a un jet privado específico.")
    @GetMapping("/buscarVueloJetPrivado/{privateJetId}")
    public List<Flight> buscarVueloJetPrivado(@PathVariable UUID privateJetId) {
        List<Flight> flights = this.serviceFlight.getFlightByPrivateJetId(privateJetId);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encuentran vuelos con el privateJet Id.");
        }
        return flights;
    }

    @Operation(summary = "Buscar vuelos por aeropuerto de salida", description = "Devuelve una lista de vuelos que salen desde el aeropuerto especificado.")
    @GetMapping("/buscarVueloSalida/{departureAirPort}")
    public List<Flight> buscarVueloAeropuertoSalida(@PathVariable String departureAirPort) {
        List<Flight> flights = this.serviceFlight.getFlightByDepartureAirports(departureAirPort);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encontraron vuelos desde el aeropuerto: " + departureAirPort);
        }
        return flights;
    }

    @Operation(summary = "Buscar vuelos por aeropuerto de llegada", description = "Devuelve una lista de vuelos que llegan al aeropuerto especificado.")
    @GetMapping("/buscarVueloLlegada/{arrivalAirPort}")
    public List<Flight> buscarVueloAeropuertoLlegada(@PathVariable String arrivalAirPort) {
        List<Flight> flights = this.serviceFlight.getFlightByArrivalAirports(arrivalAirPort);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encontraron vuelos hacia el aeropuerto: " + arrivalAirPort);
        }
        return flights;
    }


    @Operation(summary = "Buscar vuelos por hora de llegada", description = "Devuelve una lista de vuelos que llegan a la hora especificada.")
    @GetMapping("/buscarVueloHoraLlegada")
    public List<Flight> buscarVueloHoraLlegada(@RequestParam("arrivalTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime arrivalTime) {

        Timestamp timestamp = Timestamp.from(arrivalTime.toInstant());

        List<Flight> flights = this.serviceFlight.getFlightByArrivalTime(timestamp);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encontraron vuelos con hora de llegada: " + timestamp);
        }
        return flights;
    }

    @Operation(summary = "Buscar vuelos por hora de salida", description = "Devuelve una lista de vuelos que salen a la hora especificada.")
    @GetMapping("/buscarVueloHoraSalida")
    public List<Flight> buscarVueloHoraSalida(@RequestParam("departureTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime departureTime) {

        // Convertimos OffsetDateTime a Timestamp para que coincida con la base de datos
        Timestamp timestamp = Timestamp.from(departureTime.toInstant());

        List<Flight> flights = this.serviceFlight.getFlightByDepartureTime(timestamp);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encontraron vuelos con hora de salida: " + timestamp);
        }
        return flights;
    }


    @Operation(summary = "Actualizar información de un vuelo", description = "Modifica los datos de un vuelo existente.")
    @PutMapping("/update/{id}")
    public Flight update(@PathVariable UUID id, @RequestBody Flight flight) {
        Optional<Flight> existingFlight = this.serviceFlight.getFlightById(id);
        if (existingFlight.isEmpty()) {
            throw new ApiRequestException("No se puede actualizar. No se encontró el vuelo con ID " + id);
        }

        try {
            this.serviceFlight.updateFlight(id, flight);
            return flight;
        } catch (Exception e) {
            throw new ApiRequestException("Error al actualizar el vuelo con ID " + id + ": " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un vuelo", description = "Elimina un vuelo por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        Optional<Flight> existingFlight = this.serviceFlight.getFlightById(id);
        if (existingFlight.isEmpty()) {
            throw new ApiRequestException("No se puede eliminar. No se encontró el vuelo con ID " + id);
        }

        try {
            this.serviceFlight.deleteFlight(id);
        } catch (Exception e) {
            throw new ApiRequestException("Error al eliminar el vuelo con ID " + id + ": " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar vuelo por proposito", description = "Devuelve el vuelo con un proposito específico.")
    @GetMapping("/buscarVueloProposito/{purpose}")
    public List<Flight> buscarVueloProposito(@PathVariable String purpose) {
        List<Flight> flights = this.serviceFlight.getFlightByPurpose(purpose);
        if (flights.isEmpty()) {
            throw new ApiRequestException("No se encuentran vuelos con este proposito: " + purpose);
        }
        return flights;
    }
}
