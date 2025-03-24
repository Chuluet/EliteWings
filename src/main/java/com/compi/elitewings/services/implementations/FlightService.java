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
    public List<Flight> getFlightByCelebrityId(UUID celebrityId) {
        return this.flightRepository.findByCelebrityId(celebrityId);
    }

    @Override
    public List<Flight> getFlightByPrivateJetId(UUID privateJetId) {
        return this.flightRepository.findByJetId(privateJetId);
    }

    @Override
    public List<Flight> getFlightByDepartureAirports(String departureAirport) {
        return this.flightRepository.findByDepartureAirPort(departureAirport);
    }

    @Override
    public List<Flight> getFlightByArrivalAirports(String arrivalAirport) {
        return this.flightRepository.findByArrivalAirPort(arrivalAirport);
    }

    @Override
    public List<Flight> getFlightByDepartureTime(Timestamp departureTime) {
        return this.flightRepository.findByDepartureTime(departureTime);
    }

    @Override
    public List<Flight> getFlightByArrivalTime(Timestamp arrivalTime) {

        return this.flightRepository.findByArrivalTime(arrivalTime);
    }

    @Override
    public List<Flight> getFlightByPurpose(String purpose) {
        return this.flightRepository.findByPurpose(purpose);
    }

    @Override
    public void deleteFlight(UUID id) {
        this.flightRepository.deleteById(id);
    }

    @Override
    public void updateFlight(UUID id, Flight flight) {
        Optional<Flight> existingFlightOptional = this.flightRepository.findById(id);

        if (existingFlightOptional.isPresent()) {
            Flight existingFlight = existingFlightOptional.get();
            existingFlight.setCelebrityId(flight.getCelebrityId());
            existingFlight.setJetId(flight.getJetId());
            existingFlight.setDepartureAirPort(flight.getDepartureAirPort());
            existingFlight.setArrivalAirPort(flight.getArrivalAirPort());
            existingFlight.setDepartureTime(flight.getDepartureTime());
            existingFlight.setArrivalTime(flight.getArrivalTime());
            existingFlight.setPurpose(flight.getPurpose());
            this.flightRepository.save(existingFlight);
        } else {
            throw new RuntimeException("Flight with ID " + id + " not found");
        }
    }

}
