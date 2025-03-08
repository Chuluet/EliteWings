package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Airport;
import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface IAirportRepository extends JpaRepository<Airport, UUID> {
  rd fyguhi,´-
    List<Flight> findByLocation(String location);
    List<Flight> findByName(String name);
    List<Flight> findByOwners(String owners);
    List<Flight> findByCapacity(int capacity);

}
