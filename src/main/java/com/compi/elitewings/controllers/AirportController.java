package com.compi.elitewings.controllers;

import com.compi.elitewings.exception.ApiRequestException;
import com.compi.elitewings.models.Airport;
import com.compi.elitewings.services.IServiceAirport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/airports")
@Tag(name = "Airports", description = "Endpoints para la gestión de aeropuertos")
public class AirportController {
    private final IServiceAirport serviceAirport;

    @Autowired
    public AirportController(IServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    @Operation(summary = "Obtener todos los aeropuertos", description = "Devuelve una lista de todos los aeropuertos registrados.")
    @GetMapping("/")
    public List<Airport> index() {
        List<Airport> airports = this.serviceAirport.getAirport();
        if (airports.isEmpty()) {
            throw new ApiRequestException("No hay aeropuertos registrados.");
        }
        return airports;
    }

    @Operation(summary = "Agregar un nuevo aeropuerto", description = "Crea y registra un nuevo aeropuerto.")
    @PostMapping("/")
    public Airport add(@Valid @RequestBody Airport airport) {
        try {
            this.serviceAirport.addAirport(airport);
            return airport;
        } catch (Exception e) {
            throw new ApiRequestException("Error al agregar el aeropuerto: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar aeropuerto por ID", description = "Devuelve un aeropuerto basado en su ID único.")
    @GetMapping("/buscarId/{id}")
    public Airport buscar(@PathVariable UUID id) {
        return this.serviceAirport.getAirportById(id)
                .orElseThrow(() -> new ApiRequestException("No se encontró el aeropuerto con ID " + id));
    }

    @Operation(summary = "Buscar aeropuertos por ubicación", description = "Filtra aeropuertos por su ubicación.")
    @GetMapping("/buscarLugar/{lugar}")
    public List<Airport> buscarLugar(@PathVariable String lugar) {
        List<Airport> airports = this.serviceAirport.getAirportByLocation(lugar);
        if (airports.isEmpty()) {
            throw new ApiRequestException("No se encontraron aeropuertos en la ubicación: " + lugar);
        }
        return airports;
    }

    @Operation(summary = "Buscar aeropuerto por nombre", description = "Devuelve un aeropuerto basado en su nombre.")
    @GetMapping("/buscarNombre/{nombre}")
    public Airport buscarNombre(@PathVariable String nombre) {
        return this.serviceAirport.getAirportByName(nombre)
                .orElseThrow(() -> new ApiRequestException("No se encontró el aeropuerto con nombre " + nombre));
    }

    @Operation(summary = "Buscar aeropuertos por capacidad", description = "Filtra aeropuertos que tienen una capacidad específica.")
    @GetMapping("/buscarCapacidad/{capacidad}")
    public List<Airport> buscarCapacidad(@PathVariable int capacidad) {
        List<Airport> airports = this.serviceAirport.getAirportByCapacity(capacidad);
        if (airports.isEmpty()) {
            throw new ApiRequestException("No se encontraron aeropuertos con capacidad de " + capacidad);
        }
        return airports;
    }

    @Operation(summary = "Buscar aeropuerto por dueño", description = "Devuelve un aeropuerto basado en el nombre del dueño.")
    @GetMapping("/buscarOwners/{owners}")
    public List<Airport> buscarOwners(@PathVariable String owners) {
        List<Airport> airports =  this.serviceAirport.getAirportByOwners(owners);
        if (airports.isEmpty()) {
            throw new ApiRequestException("No se encontraron aeropuertos con el owner: " + owners);
        }
        return airports;
    }

    @Operation(summary = "Actualizar aeropuerto", description = "Modifica la información de un aeropuerto existente.")
    @PutMapping("/update/{id}")
    public Airport update(@PathVariable UUID id, @Valid @RequestBody Airport airport) {
        Optional<Airport> existingAirport = this.serviceAirport.getAirportById(id);
        if (existingAirport.isEmpty()) {
            throw new ApiRequestException("No se puede actualizar. No se encontró el aeropuerto con ID " + id);
        }

        try {
            this.serviceAirport.updateAirport(id, airport);
            return airport;
        } catch (Exception e) {
            throw new ApiRequestException("Error al actualizar el aeropuerto con ID " + id + ": " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar aeropuerto", description = "Elimina un aeropuerto por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        Optional<Airport> existingAirport = this.serviceAirport.getAirportById(id);
        if (existingAirport.isEmpty()) {
            throw new ApiRequestException("No se puede eliminar. No se encontró el aeropuerto con ID " + id);
        }

        try {
            this.serviceAirport.deleteAirport(id);
        } catch (Exception e) {
            throw new ApiRequestException("Error al eliminar el aeropuerto con ID " + id + ": " + e.getMessage());
        }
    }
}
