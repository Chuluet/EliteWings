package com.compi.elitewings.controllers;

import com.compi.elitewings.exception.ApiRequestException;
import com.compi.elitewings.models.PrivateJet;
import com.compi.elitewings.services.IServicePrivateJet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        List<PrivateJet> privateJets = this.servicePrivateJet.getPrivateJet();
        if (privateJets.isEmpty()) {
            throw new ApiRequestException("No hay jets privados registrados.");
        }
        return privateJets;
    }

    @Operation(summary = "Agregar un nuevo jet privado", description = "Registra un nuevo jet privado en la base de datos.")
    @PostMapping("/")
    public PrivateJet add(@Valid @RequestBody PrivateJet privateJet) {
        try {
            this.servicePrivateJet.addPrivateJet(privateJet);
            return privateJet;
        } catch (Exception e) {
            throw new ApiRequestException("Error al agregar el jet privado: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar jet privado por ID", description = "Devuelve un jet privado específico según su ID único.")
    @GetMapping("/buscar/{id}")
    public PrivateJet buscar(@PathVariable UUID id) {
        return this.servicePrivateJet.getPrivateJetById(id)
                .orElseThrow(() -> new ApiRequestException("No se encontró el jet privado con ID " + id));
    }

    @Operation(summary = "Buscar jets privados por modelo", description = "Devuelve una lista de jets privados filtrados por modelo.")
    @GetMapping("/buscarModelo/{modelo}")
    public List<PrivateJet> buscarModelo(@PathVariable String modelo) {
        List<PrivateJet> jets = this.servicePrivateJet.getPrivateJetByModel(modelo);
        if (jets.isEmpty()) {
            throw new ApiRequestException("No se encontraron jets privados con el modelo: " + modelo);
        }
        return jets;
    }

    @Operation(summary = "Buscar jets privados por capacidad", description = "Devuelve una lista de jets privados filtrados por su capacidad de pasajeros.")
    @GetMapping("/buscarCapacidad/{capacidad}")
    public List<PrivateJet> buscarCapacidad(@PathVariable Integer capacidad) {
        List<PrivateJet> jets = this.servicePrivateJet.getPrivateJetByCapacity(capacidad);
        if (jets.isEmpty()) {
            throw new ApiRequestException("No se encontraron jets privados con capacidad: " + capacidad);
        }
        return jets;
    }

    @Operation(summary = "Buscar jet privado por owner", description = "Devuelve un jet privado asociado a un owner.")
    @GetMapping("/buscarOwner/{ownerId}")
    public List<PrivateJet> buscarOwner(@PathVariable UUID ownerId) {
        List<PrivateJet> jets = this.servicePrivateJet.getPrivateJetByOwner(ownerId);
        if (jets.isEmpty()) {
            throw new ApiRequestException("No se encontraron jets privados con el ownerId: " + ownerId);
        }
        return jets;

    }

    @Operation(summary = "Actualizar información de un jet privado", description = "Modifica los datos de un jet privado existente.")
    @PutMapping("/update/{id}")
    public PrivateJet update(@PathVariable UUID id, @Valid @RequestBody PrivateJet privateJet) {
        Optional<PrivateJet> existingPrivateJet = this.servicePrivateJet.getPrivateJetById(id);
        if (existingPrivateJet.isEmpty()) {
            throw new ApiRequestException("No se puede actualizar. No se encontró el jet privado con ID " + id);
        }

        try {
            this.servicePrivateJet.updatePrivateJet(id, privateJet);
            return privateJet;
        } catch (Exception e) {
            throw new ApiRequestException("Error al actualizar el jet privado con ID " + id + ": " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un jet privado", description = "Elimina un jet privado por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        Optional<PrivateJet> existingPrivateJet = this.servicePrivateJet.getPrivateJetById(id);
        if (existingPrivateJet.isEmpty()) {
            throw new ApiRequestException("No se puede eliminar. No se encontró el jet privado con ID " + id);
        }

        try {
            this.servicePrivateJet.deletePrivateJet(id);
        } catch (Exception e) {
            throw new ApiRequestException("Error al eliminar el jet privado con ID " + id + ": " + e.getMessage());
        }
    }
}
