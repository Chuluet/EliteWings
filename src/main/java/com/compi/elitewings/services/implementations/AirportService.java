package com.compi.elitewings.services.implementations;

import com.compi.elitewings.models.Airport;
import com.compi.elitewings.repositories.IAirportRepository;
import com.compi.elitewings.services.IServiceAirport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirportService implements IServiceAirport {
    private final IAirportRepository airportRepository;

    @Autowired
    public AirportService(IAirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> getAirport() {
        return this.airportRepository.findAll();
    }

    @Override
    public void addAirport(Airport airport) {
        this.airportRepository.save(airport);
    }

    @Override
    public Optional<Airport> getAirportById(UUID id) {
        return this.airportRepository.findById(id);
    }

    @Override
    public Optional<Airport> getAirportByName(String name) {
        return this.airportRepository.findByName(name);
    }

    @Override
    public List<Airport> getAirportByCapacity(int capacity) {
        return this.airportRepository.findByCapacity(capacity);
    }

    @Override
    public Optional<Airport> getAirportByOwners(String owners) {
        return this.airportRepository.findByOwners(owners);
    }

    @Override
    public List<Airport> getAirportByLocation(String location) {
        return this.airportRepository.findByLocation(location);
    }
}
