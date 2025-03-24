package com.compi.elitewings.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "Airports")

public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private UUID id;
    @NotBlank(message = "El nombre no puede ser nulo o vacío.")
    private String name;
    @NotBlank(message = "la locacion no puede ser nulo o vacío.")
    private String location;
    @NotNull(message = "la capacidad no puede ser nulo o vacío.")
    @Min(value = 1, message = "La capacidad debe ser al menos 1.")
    private int capacity;
    @Column(nullable = false)
    private String owners;
    @PrePersist
    public void setDefaultOwners() {
        if (owners == null || owners.trim().isEmpty()) {
            owners = "Sofia and Natalia are the best owners";
        }
    }
}
