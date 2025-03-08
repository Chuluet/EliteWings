package com.compi.elitewings.repositories;

import com.compi.elitewings.models.SecurityReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISecurityReportRepository extends JpaRepository<SecurityReport, UUID> {
    Optional<SecurityReport> findByFlight_id(UUID flightId);
    List<SecurityReport> findByReported_by(String reported_by);
}
