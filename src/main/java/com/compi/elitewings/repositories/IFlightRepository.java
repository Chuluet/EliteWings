package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public interface IFlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findByCelebrity_Id(UUID celebrity_Id);
    List<Flight> findByJet_Id(UUID jet_Id);
    List<Flight> findByDeparture_airPort(String departure_airPort);
    List<Flight> findByArrival_airPort(String arrival_airPort);
    List<Flight> findByDeparture_time(Timestamp departure_time);
    List<Flight> findByArrival_time(Timestamp arrival_time);
    List<Flight> findByPurpose(String purpose);
}
