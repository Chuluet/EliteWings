package com.compi.elitewings.services.implementations;

import com.compi.elitewings.models.SecurityReport;
import com.compi.elitewings.repositories.ISecurityReportRepository;
import com.compi.elitewings.services.IServiceSecurityReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityReportService implements IServiceSecurityReport {

    private final ISecurityReportRepository securityReportRepository;

    @Autowired
    public SecurityReportService(ISecurityReportRepository securityReportRepository) {
        this.securityReportRepository = securityReportRepository;
    }


    @Override
    public List<SecurityReport> getSecurityReport() {
        return this.securityReportRepository.findAll();
    }

    @Override
    public void addSecurityReport(SecurityReport securityReport) {
        this.securityReportRepository.save(securityReport);
    }

    @Override
    public Optional<SecurityReport> getSecurityReportById(UUID id) {
        return this.securityReportRepository.findById(id);
    }

    @Override
    public Optional<SecurityReport> getSecurityReportByFlightId(UUID flightId) {
        return this.securityReportRepository.findByFlight_id(flightId);
    }

    @Override
    public List<SecurityReport> getSecurityReportByReportedBy(String reported_by) {
        return this.securityReportRepository.findByReported_by(reported_by);
    }
}
