package com.compi.elitewings.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SecurityReports")
public class SecurityReport {
    @Id
    private UUID id;
    private UUID flightId;
    private String reportedBy;
    private String description;
    private boolean isResolved;
}
