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
@Table(name = "PrivateJets")
public class PrivateJet {
    @Id
    private UUID id;
    private String model;
    private int capacity;
    private UUID pilot_id;
}
