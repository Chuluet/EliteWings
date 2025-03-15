package com.compi.elitewings.services;

import com.compi.elitewings.models.Airport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IServiceAirport {
    List<Airport> getAirport();

    void addAirport(Airport airport);

     Optional<Airport> getAirportById(UUID id);

    Optional<Airport> getAirportByName(String name);

    List<Airport> getAirportByCapacity(int capacity);

     Optional<Airport> getAirportByOwners(String owners);

     List<Airport> getAirportByLocation(String location);

    void deleteAirport(UUID id);

    void updateAirport(UUID id, Airport airport);
}
