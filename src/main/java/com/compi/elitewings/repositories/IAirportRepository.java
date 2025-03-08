package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Airport;
import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAirportRepository extends JpaRepository<Airport, UUID> {
    List<Airport> findByLocation(String location);

    Optional<Airport> findByName(String name);

    Optional<Airport> findByOwners(String owners);

    List<Airport> findByCapacity(int capacity);

}
