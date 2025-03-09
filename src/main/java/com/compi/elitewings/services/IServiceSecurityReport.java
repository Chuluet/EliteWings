package com.compi.elitewings.services;

import com.compi.elitewings.models.SecurityReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IServiceSecurityReport {
    public List<SecurityReport> getSecurityReport();

    public void addSecurityReport(SecurityReport securityReport);

    public Optional<SecurityReport> getSecurityReportById(UUID id);

    public Optional<SecurityReport> getSecurityReportByFlightId(UUID flightId);

    public List<SecurityReport> getSecurityReportByReportedBy(String reportedBy);

    public List<SecurityReport> getSecurityReportByResolved(boolean resolved);

}
