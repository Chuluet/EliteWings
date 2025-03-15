package com.compi.elitewings.repositories;


import com.compi.elitewings.models.SecurityReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISecurityReportRepository extends JpaRepository<SecurityReport, UUID> {
    Optional<SecurityReport> findByFlightId(UUID flightId);

    List<SecurityReport> findByReportedBy(String reportedBy);

    List<SecurityReport> findByisResolved(boolean isResolved);
}
