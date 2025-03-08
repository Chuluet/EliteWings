package com.compi.elitewings.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="celebrities")
public class Celebrity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String profession;

    private double netWorth;

    private boolean suspiciousActivity;
}
