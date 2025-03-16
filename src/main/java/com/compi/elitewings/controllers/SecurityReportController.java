package com.compi.elitewings.controllers;

import com.compi.elitewings.models.SecurityReport;
import com.compi.elitewings.services.IServiceSecurityReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return this.serviceSecurityReport.getSecurityReport();
    }

    @Operation(summary = "Agregar un nuevo reporte de seguridad", description = "Registra un nuevo reporte de seguridad en la base de datos.")
    @PostMapping("/")
    public SecurityReport add(@RequestBody SecurityReport securityReport) {
        this.serviceSecurityReport.addSecurityReport(securityReport);
        return securityReport;
    }

    @Operation(summary = "Buscar reporte de seguridad por ID", description = "Devuelve un reporte de seguridad específico según su ID único.")
    @GetMapping("/buscarId/{id}")
    public SecurityReport buscar(@PathVariable UUID id) {
        return this.serviceSecurityReport.getSecurityReportById(id).orElse(null);
    }

    @Operation(summary = "Buscar reporte de seguridad por ID de vuelo", description = "Devuelve un reporte de seguridad asociado a un vuelo específico.")
    @GetMapping("/buscarVueloId/{id}")
    public SecurityReport buscarVueloId(@PathVariable UUID id) {
        return this.serviceSecurityReport.getSecurityReportByFlightId(id).orElse(null);
    }

    @Operation(summary = "Buscar reportes de seguridad por reportante", description = "Devuelve una lista de reportes de seguridad generados por un usuario específico.")
    @GetMapping("/buscarReportadoPor/{nombre}")
    public List<SecurityReport> buscarReportado(@PathVariable String nombre) {
        return this.serviceSecurityReport.getSecurityReportByReportedBy(nombre);
    }

    @Operation(summary = "Buscar reportes de seguridad resueltos o pendientes", description = "Devuelve una lista de reportes de seguridad según su estado de resolución.")
    @GetMapping("/buscarResueltos/{isResolved}")
    public List<SecurityReport> buscarResuelto(@PathVariable boolean isResolved) {
        return this.serviceSecurityReport.getSecurityReportByResolved(isResolved);
    }

    @Operation(summary = "Actualizar un reporte de seguridad", description = "Modifica los datos de un reporte de seguridad existente.")
    @PutMapping("/update/{id}")
    public SecurityReport update(@PathVariable UUID id, @RequestBody SecurityReport securityReport) {
        this.serviceSecurityReport.updateSecurityReport(id, securityReport);
        return securityReport;
    }

    @Operation(summary = "Eliminar un reporte de seguridad", description = "Elimina un reporte de seguridad por su ID.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        this.serviceSecurityReport.deleteReport(id);
    }
}
