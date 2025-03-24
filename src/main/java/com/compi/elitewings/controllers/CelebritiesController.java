package com.compi.elitewings.controllers;

import com.compi.elitewings.exception.ApiRequestException;
import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.services.IServiceCelebrity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/celebrities")
@Tag(name = "Celebrities", description = "Endpoints para la gestión de celebridades")
public class CelebritiesController {
    private final IServiceCelebrity serviceCelebrity;

    @Autowired
    public CelebritiesController(IServiceCelebrity serviceCelebrity) {
        this.serviceCelebrity = serviceCelebrity;
    }

    @Operation(summary = "Obtener todas las celebridades", description = "Devuelve una lista de todas las celebridades registradas.")
    @GetMapping("/")
    public List<Celebrity> index() {
        List<Celebrity> celebrities = this.serviceCelebrity.getCelebrity();
        if (celebrities.isEmpty()) {
            throw new ApiRequestException("No hay celebridades registradas.");
        }
        return celebrities;
    }

    @Operation(summary = "Agregar una nueva celebridad", description = "Registra una nueva celebridad en la base de datos.")
    @PostMapping("/")
    public Celebrity add(@Valid @RequestBody Celebrity celebrity) {
        try {
            this.serviceCelebrity.addCelebrity(celebrity);
            return celebrity;
        } catch (Exception e) {
            throw new ApiRequestException("Error al agregar la celebridad: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar celebridad por ID", description = "Devuelve una celebridad específica según su ID único.")
    @GetMapping("/buscar/{id}")
    public Celebrity buscar(@PathVariable UUID id) {
        return this.serviceCelebrity.getCelebrityById(id)
                .orElseThrow(() -> new ApiRequestException("No se encontró la celebridad con ID " + id));
    }

    @Operation(summary = "Buscar celebridades por nombre", description = "Devuelve una lista de celebridades con el nombre indicado.")
    @GetMapping("/buscarNombre/{nombre}")
    public List<Celebrity> buscarNombre(@PathVariable String nombre) {
        List<Celebrity> celebrities = this.serviceCelebrity.getCelebrityByName(nombre);
        if (celebrities.isEmpty()) {
            throw new ApiRequestException("No se encontraron celebridades con el nombre: " + nombre);
        }
        return celebrities;
    }

    @Operation(summary = "Buscar celebridades con actividad sospechosa", description = "Devuelve una lista de celebridades marcadas como sospechosas.")
    @GetMapping("/buscarSospechosas/{sospechosa}")
    public List<Celebrity> buscarSospechosa(@PathVariable boolean sospechosa) {
        List<Celebrity> celebrities = this.serviceCelebrity.getCelebrityBySuspiciousActivity(sospechosa);
        if (celebrities.isEmpty()) {
            throw new ApiRequestException("No se encontraron celebridades con actividad sospechosa: " + sospechosa);
        }
        return celebrities;
    }

    @Operation(summary = "Actualizar información de una celebridad", description = "Modifica los datos de una celebridad existente.")
    @PutMapping("/update/{id}")
    public Celebrity update(@PathVariable UUID id, @Valid @RequestBody Celebrity celebrity) {
        Optional<Celebrity> existingCelebrity = this.serviceCelebrity.getCelebrityById(id);
        if (existingCelebrity.isEmpty()) {
            throw new ApiRequestException("No se puede actualizar. No se encontró la celebridad con ID " + id);
        }

        try {
            this.serviceCelebrity.updateCelebrity(id, celebrity);
            return celebrity;
        } catch (Exception e) {
            throw new ApiRequestException("Error al actualizar la celebridad con ID " + id + ": " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una celebridad", description = "Elimina una celebridad por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        Optional<Celebrity> existingCelebrity = this.serviceCelebrity.getCelebrityById(id);
        if (existingCelebrity.isEmpty()) {
            throw new ApiRequestException("No se puede eliminar. No se encontró la celebridad con ID " + id);
        }

        try {
            this.serviceCelebrity.deleteCelebrity(id);
        } catch (Exception e) {
            throw new ApiRequestException("Error al eliminar la celebridad con ID " + id + ": " + e.getMessage());
        }
    }
}
