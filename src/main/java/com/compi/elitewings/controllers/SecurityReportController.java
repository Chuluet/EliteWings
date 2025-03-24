package com.compi.elitewings.controllers;

import com.compi.elitewings.exception.ApiRequestException;
import com.compi.elitewings.models.SecurityReport;
import com.compi.elitewings.services.IServiceSecurityReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/securityReport")
@Tag(name = "Security Reports", description = "Gestión de reportes de seguridad en vuelos")
public class SecurityReportController {
    private final IServiceSecurityReport serviceSecurityReport;

    @Autowired
    public SecurityReportController(IServiceSecurityReport serviceSecurityReport) {
        this.serviceSecurityReport = serviceSecurityReport;
    }

    @Operation(summary = "Obtener todos los reportes de seguridad", description = "Devuelve una lista de todos los reportes de seguridad registrados.")
    @GetMapping("/")
    public List<SecurityReport> index() {
        List<SecurityReport> reports = this.serviceSecurityReport.getSecurityReport();
        if (reports.isEmpty()) {
            throw new ApiRequestException("No hay reportes de seguridad registrados.");
        }
        return reports;
    }

    @Operation(summary = "Agregar un nuevo reporte de seguridad", description = "Registra un nuevo reporte de seguridad en la base de datos.")
    @PostMapping("/")
    public SecurityReport add(@Valid @RequestBody SecurityReport securityReport) {
        try {
            this.serviceSecurityReport.addSecurityReport(securityReport);
            return securityReport;
        } catch (Exception e) {
            throw new ApiRequestException("Error al agregar el reporte de seguridad: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar reporte de seguridad por ID", description = "Devuelve un reporte de seguridad específico según su ID único.")
    @GetMapping("/buscar/{id}")
    public SecurityReport buscar(@PathVariable UUID id) {
        return this.serviceSecurityReport.getSecurityReportById(id)
                .orElseThrow(() -> new ApiRequestException("No se encontró el reporte de seguridad con ID " + id));
    }

    @Operation(summary = "Buscar reporte de seguridad por ID de vuelo", description = "Devuelve un reporte de seguridad asociado a un vuelo específico.")
    @GetMapping("/buscarVueloId/{id}")
    public SecurityReport buscarVueloId(@PathVariable UUID id) {
        return this.serviceSecurityReport.getSecurityReportByFlightId(id)
                .orElseThrow(() -> new ApiRequestException("No se encontró un reporte de seguridad para el vuelo con ID " + id));
    }

    @Operation(summary = "Buscar reportes de seguridad por reportante", description = "Devuelve una lista de reportes de seguridad generados por un usuario específico.")
    @GetMapping("/buscarReportadoPor/{nombre}")
    public List<SecurityReport> buscarReportado(@PathVariable String nombre) {
        List<SecurityReport> reports = this.serviceSecurityReport.getSecurityReportByReportedBy(nombre);
        if (reports.isEmpty()) {
            throw new ApiRequestException("No se encontraron reportes de seguridad para el usuario " + nombre);
        }
        return reports;
    }

    @Operation(summary = "Buscar reportes de seguridad resueltos o pendientes", description = "Devuelve una lista de reportes de seguridad según su estado de resolución.")
    @GetMapping("/buscarResueltos/{isResolved}")
    public List<SecurityReport> buscarResuelto(@PathVariable boolean isResolved) {
        List<SecurityReport> reports = this.serviceSecurityReport.getSecurityReportByResolved(isResolved);
        if (reports.isEmpty()) {
            throw new ApiRequestException("No se encontraron reportes de seguridad con estado resuelto: " + isResolved);
        }
        return reports;
    }

    @Operation(summary = "Actualizar un reporte de seguridad", description = "Modifica los datos de un reporte de seguridad existente.")
    @PutMapping("/update/{id}")
    public SecurityReport update(@PathVariable UUID id, @Valid @RequestBody SecurityReport securityReport) {
        Optional<SecurityReport> existingReport = this.serviceSecurityReport.getSecurityReportById(id);
        if (existingReport.isEmpty()) {
            throw new ApiRequestException("No se puede actualizar. No se encontró el reporte de seguridad con ID " + id);
        }

        try {
            this.serviceSecurityReport.updateSecurityReport(id, securityReport);
            return securityReport;
        } catch (Exception e) {
            throw new ApiRequestException("Error al actualizar el reporte de seguridad con ID " + id + ": " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un reporte de seguridad", description = "Elimina un reporte de seguridad por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        Optional<SecurityReport> existingReport = this.serviceSecurityReport.getSecurityReportById(id);
        if (existingReport.isEmpty()) {
            throw new ApiRequestException("No se puede eliminar. No se encontró el reporte de seguridad con ID " + id);
        }

        try {
            this.serviceSecurityReport.deleteReport(id);
        } catch (Exception e) {
            throw new ApiRequestException("Error al eliminar el reporte de seguridad con ID " + id + ": " + e.getMessage());
        }
    }
}
