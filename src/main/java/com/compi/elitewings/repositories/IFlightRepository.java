package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public interface IFlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findByCelebrityId(UUID celebrityId);

    List<Flight> findByJetId(UUID jetId);

    List<Flight> findByDepartureAirPort(String departureAirPort);

    List<Flight> findByArrivalAirPort(String arrivalAirPort);

    List<Flight> findByDepartureTime(Timestamp departureTime);

    List<Flight> findByArrivalTime(Timestamp arrivalTime);

    List<Flight> findByPurpose(String purpose);
}
