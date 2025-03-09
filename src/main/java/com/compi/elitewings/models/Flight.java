package com.compi.elitewings.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Flights")
public class Flight {
    @JsonIgnore
    @Id
    private UUID id;
    private UUID celebrity_id;
    private UUID jet_id;
    private String departure_airPort;
    private String arrival_airPort;
    private Timestamp departure_time;
    private Timestamp arrival_time;
    private String purpose;
}
