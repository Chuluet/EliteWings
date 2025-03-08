package com.compi.elitewings.repositories;

import com.compi.elitewings.models.SecurityReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ISecurityReportRepository extends JpaRepository<SecurityReport, UUID> {
    List<SecurityReport> findBySecurityReport_id(UUID securityReport_Id);
    List<SecurityReport> findByFlight_id(UUID flight_Id);
    List<SecurityReport> findByReported_by(String reported_by);
    List<SecurityReport> findByDescription(String description);
    List<SecurityReport> findByIs_resolved(boolean is_resolved);
}
