package com.compi.elitewings.services;

import com.compi.elitewings.models.Flight;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IServiceFlight {
    public List<Flight> getFlights();
    public void addFlight(Flight flight);
    public Optional<Flight> getFlightById(UUID id);
    public Optional<Flight> getFlightByCelebrityId(UUID celebrityId);
    public Optional<Flight> getFlightByPrivateJetId(UUID privateJetId);
    public List<Flight> getFlightByDepartureAirports(String departureAirport);
    public List<Flight> getFlightByArrivalAirports(String arrivalAirport);
    public List<Flight> getFlightByDepartureTime(Timestamp departureTime);
    public List<Flight> getFlightByArrivalTime(Timestamp arrivalTime);
    public void deleteFlight(UUID id);
    public void updateFlight(UUID id, Flight flight);
}
