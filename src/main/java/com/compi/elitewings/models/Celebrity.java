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
@Table(name = "Celebrities")
public class Celebrity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()") // PostgreSQL
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "El nombre no puede ser nulo o vacío.")
    private String name;

    @NotBlank(message = "La profesión no puede ser nula o vacía.")
    private String profession;

    private double netWorth;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean suspiciousActivity = false;
}
