package com.compi.elitewings.controllers;

import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.services.IServiceCelebrity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return this.serviceCelebrity.getCelebrity();
    }

    @Operation(summary = "Agregar una nueva celebridad", description = "Registra una nueva celebridad en la base de datos.")
    @PostMapping("/")
    public Celebrity add(@RequestBody Celebrity celebrity) {
        this.serviceCelebrity.addCelebrity(celebrity);
        return celebrity;
    }

    @Operation(summary = "Buscar celebridad por ID", description = "Devuelve una celebridad específica según su ID único.")
    @GetMapping("/buscar/{id}")
    public Celebrity buscar(@PathVariable UUID id) {
        return this.serviceCelebrity.getCelebrityById(id).orElse(null);
    }

    @Operation(summary = "Buscar celebridades por nombre", description = "Devuelve una lista de celebridades con el nombre indicado.")
    @GetMapping("/buscarNombre/{nombre}")
    public List<Celebrity> buscarNombre(@PathVariable String nombre) {
        return this.serviceCelebrity.getCelebrityByName(nombre);
    }

    @Operation(summary = "Buscar celebridades con actividad sospechosa", description = "Devuelve una lista de celebridades marcadas como sospechosas.")
    @GetMapping("/buscarSospechosas/{sospechosa}")
    public List<Celebrity> buscarSospechosa(@PathVariable boolean sospechosa) {
        return this.serviceCelebrity.getCelebrityBySuspiciousActivity(sospechosa);
    }

    @Operation(summary = "Actualizar información de una celebridad", description = "Modifica los datos de una celebridad existente.")
    @PutMapping("/update/{id}")
    public Celebrity update(@PathVariable UUID id, @RequestBody Celebrity celebrity) {
        this.serviceCelebrity.updateCelebrity(id, celebrity);
        return celebrity;
    }

    @Operation(summary = "Eliminar una celebridad", description = "Elimina una celebridad por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        this.serviceCelebrity.deleteCelebrity(id);
    }
}
