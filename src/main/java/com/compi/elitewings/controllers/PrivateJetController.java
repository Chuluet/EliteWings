package com.compi.elitewings.controllers;

import com.compi.elitewings.models.PrivateJet;
import com.compi.elitewings.services.IServicePrivateJet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/privateJet")
@Tag(name = "Private Jets", description = "Endpoints para la gestión de jets privados")
public class PrivateJetController {
    private final IServicePrivateJet servicePrivateJet;

    @Autowired
    public PrivateJetController(IServicePrivateJet servicePrivateJet) {
        this.servicePrivateJet = servicePrivateJet;
    }

    @Operation(summary = "Obtener todos los jets privados", description = "Devuelve una lista de todos los jets privados registrados.")
    @GetMapping("/")
    public List<PrivateJet> index() {
        return this.servicePrivateJet.getPrivateJet();
    }

    @Operation(summary = "Agregar un nuevo jet privado", description = "Registra un nuevo jet privado en la base de datos.")
    @PostMapping("/")
    public PrivateJet add(@RequestBody PrivateJet privateJet) {
        this.servicePrivateJet.addPrivateJet(privateJet);
        return privateJet;
    }

    @Operation(summary = "Buscar jet privado por ID", description = "Devuelve un jet privado específico según su ID único.")
    @GetMapping("/buscar/{id}")
    public PrivateJet buscar(@PathVariable UUID id) {
        return this.servicePrivateJet.getPrivateJetById(id).orElse(null);
    }

    @Operation(summary = "Buscar jets privados por modelo", description = "Devuelve una lista de jets privados filtrados por modelo.")
    @GetMapping("/buscarModelo/{modelo}")
    public List<PrivateJet> buscarModelo(@PathVariable String modelo) {
        return this.servicePrivateJet.getPrivateJetByModel(modelo);
    }

    @Operation(summary = "Buscar jets privados por capacidad", description = "Devuelve una lista de jets privados filtrados por su capacidad de pasajeros.")
    @GetMapping("/buscarCapacidad/{capacidad}")
    public List<PrivateJet> buscarCapacidad(@PathVariable int capacidad) {
        return this.servicePrivateJet.getPrivateJetByCapacity(capacidad);
    }

    @Operation(summary = "Buscar jet privado por piloto", description = "Devuelve un jet privado asociado a un piloto específico.")
    @GetMapping("/buscarPiloto/{pilotId}")
    public PrivateJet buscarPiloto(@PathVariable UUID pilotId) {
        return this.servicePrivateJet.getPrivateJetByPilot(pilotId).orElse(null);
    }

    @Operation(summary = "Actualizar información de un jet privado", description = "Modifica los datos de un jet privado existente.")
    @PutMapping("/update/{id}")
    public PrivateJet update(@PathVariable UUID id, @RequestBody PrivateJet privateJet) {
        this.servicePrivateJet.updatePrivateJet(id, privateJet);
        return privateJet;
    }

    @Operation(summary = "Eliminar un jet privado", description = "Elimina un jet privado por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        this.servicePrivateJet.deletePrivateJet(id);
    }
}
