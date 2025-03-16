package com.compi.elitewings.controllers;

import com.compi.elitewings.models.Airport;
import com.compi.elitewings.services.IServiceAirport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return this.serviceAirport.getAirport();
    }

    @Operation(summary = "Agregar un nuevo aeropuerto", description = "Crea y registra un nuevo aeropuerto.")
    @PostMapping("/")
    public Airport add(@RequestBody Airport airport) {
        this.serviceAirport.addAirport(airport);
        return airport;
    }

    @Operation(summary = "Buscar aeropuerto por ID", description = "Devuelve un aeropuerto basado en su ID único.")
    @GetMapping("/buscarId/{id}")
    public Airport buscar(@PathVariable UUID id) {
        return this.serviceAirport.getAirportById(id).orElse(null);
    }

    @Operation(summary = "Buscar aeropuertos por ubicación", description = "Filtra aeropuertos por su ubicación.")
    @GetMapping("/buscarLugar/{lugar}")
    public List<Airport> buscarLugar(@PathVariable String lugar) {
        return this.serviceAirport.getAirportByLocation(lugar);
    }

    @Operation(summary = "Buscar aeropuerto por nombre", description = "Devuelve un aeropuerto basado en su nombre.")
    @GetMapping("/buscarNombre/{nombre}")
    public Airport buscarNombre(@PathVariable String nombre) {
        return this.serviceAirport.getAirportByName(nombre).orElse(null);
    }

    @Operation(summary = "Buscar aeropuertos por capacidad", description = "Filtra aeropuertos que tienen una capacidad específica.")
    @GetMapping("/buscarCapacidad/{capacidad}")
    public List<Airport> buscarCapacidad(@PathVariable int capacidad) {
        return this.serviceAirport.getAirportByCapacity(capacidad);
    }

    @Operation(summary = "Buscar aeropuerto por dueño", description = "Devuelve un aeropuerto basado en el nombre del dueño.")
    @GetMapping("/buscarOwners/{owners}")
    public Airport buscarOwners(@PathVariable String owners) {
        return this.serviceAirport.getAirportByOwners(owners).orElse(null);
    }

    @Operation(summary = "Actualizar aeropuerto", description = "Modifica la información de un aeropuerto existente.")
    @PutMapping("/update/{id}")
    public Airport update(@PathVariable UUID id, @RequestBody Airport airport) {
        this.serviceAirport.updateAirport(id, airport);
        return airport;
    }

    @Operation(summary = "Eliminar aeropuerto", description = "Elimina un aeropuerto por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        this.serviceAirport.deleteAirport(id);
    }
}
