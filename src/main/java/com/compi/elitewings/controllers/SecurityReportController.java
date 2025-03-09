package com.compi.elitewings.controllers;

import com.compi.elitewings.models.SecurityReport;
import com.compi.elitewings.services.IServiceSecurityReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/SecurityReport")
public class SecurityReportController {
    private final IServiceSecurityReport serviceSecurityReport;

    @Autowired
    public SecurityReportController(IServiceSecurityReport serviceSecurityReport) {
        this.serviceSecurityReport = serviceSecurityReport;
    }

    @GetMapping("/")
    public List<SecurityReport> index() {
        return this.serviceSecurityReport.getSecurityReport();
    }

    @PostMapping("/")
    public SecurityReport add(@RequestBody SecurityReport securityReport) {
        this.serviceSecurityReport.addSecurityReport(securityReport);
        return securityReport;
    }

    @GetMapping("/buscarId/{id}")
    public SecurityReport buscar(@PathVariable UUID id) {
        return this.serviceSecurityReport.getSecurityReportById(id)
                .orElse(null);
    }

    @GetMapping("/buscarVueloId/{id}")
    public SecurityReport buscarVueloId(@PathVariable UUID id) {
        return this.serviceSecurityReport.getSecurityReportByFlightId(id).orElse(null);
    }

    @GetMapping("/buscarReportadoPor/{nombre}")
    public List<SecurityReport> buscarReportado(@PathVariable String nombre) {
        return this.serviceSecurityReport.getSecurityReportByReportedBy(nombre);
    }

    @GetMapping("/buscarResueltos/{resuelto}")
    public List<SecurityReport> buscarResuelto(@PathVariable boolean resuelto) {
        return this.serviceSecurityReport.getSecurityReportByResolved(resuelto);
    }
}
