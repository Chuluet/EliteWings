package com.compi.elitewings.services.implementations;

import com.compi.elitewings.models.Flight;
import com.compi.elitewings.repositories.IFlightRepository;
import com.compi.elitewings.services.IServiceFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService implements IServiceFlight {
    private final IFlightRepository flightRepository;
    @Autowired
    public FlightService(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    @Override
    public List<Flight> getFlights() {
        return this.flightRepository.findAll();
    }

    @Override
    public void addFlight(Flight flight) {
        this.flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getFlightById(UUID id) {
        return this.flightRepository.findById(id);
    }

    @Override
    public Optional<Flight> getFlightByCelebrityId(UUID celebrityId) {
        return this.getFlightByCelebrityId(celebrityId);
    }

    @Override
    public Optional<Flight> getFlightByPrivateJetId(UUID privateJetId) {
        return this.getFlightByPrivateJetId(privateJetId);
    }

    @Override
    public List<Flight> getFlightByDepartureAirports(String departureAirport) {
        return getFlightByDepartureAirports(departureAirport);
    }

    @Override
    public List<Flight> getFlightByArrivalAirports(String arrivalAirport) {
        return getFlightByArrivalAirports(arrivalAirport);
    }

    @Override
    public List<Flight> getFlightByDepartureTime(Timestamp departureTime) {
        return getFlightByDepartureTime(departureTime);
    }

    @Override
    public List<Flight> getFlightByArrivalTime(Timestamp arrivalTime) {
        return getFlightByArrivalTime(arrivalTime);
    }
}
