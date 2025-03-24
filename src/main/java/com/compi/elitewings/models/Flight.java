package com.compi.elitewings.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()") // PostgreSQL
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private UUID celebrityId;
    private UUID jetId;
    @NotBlank(message = "El aereopuerto de salida no puede ser nulo o vacío.")
    private String departureAirPort;
    @NotBlank(message = "El aereopuerto de llegada no puede ser nulo o vacío.")
    private String arrivalAirPort;
    @NotNull(message = "La fecha de salida no puede ser nula.")
    private Timestamp departureTime;
    @NotNull(message = "La fecha de llegada no puede ser nula.")
    private Timestamp arrivalTime;
    @Pattern(regexp = "Bussiness|Vacation|Suspicious", message = "Tipo de proposito no válido")
    private String purpose;
}
