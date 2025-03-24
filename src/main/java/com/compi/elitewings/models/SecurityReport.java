package com.compi.elitewings.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private UUID flightId;
    @NotBlank(message = "asegurese que reportedBy no sea null o vacio")
    private String reportedBy;
    @NotBlank(message = "asegurese que la descripcion no sea null o vacia")
    private String description;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isResolved = false;
}
