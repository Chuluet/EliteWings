package com.compi.elitewings.services;

import com.compi.elitewings.models.Airport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IServiceAirport {
    public List<Airport> getAirport();

    public void addAirport(Airport airport);

    public Optional<Airport> getAirportById(UUID id);

    public Optional<Airport> getAirportByName(String name);

    public  List<Airport> getAirportByCapacity(int capacity);

    public Optional<Airport> getAirportByOwners(String owners);

    public List<Airport> getAirportByLocation(String location);

    public void deleteAirport(UUID id);

    public void updateAirport(UUID id, Airport airport);
}
