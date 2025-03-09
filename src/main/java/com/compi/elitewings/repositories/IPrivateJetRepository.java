package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.models.Flight;
import com.compi.elitewings.models.PrivateJet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface IPrivateJetRepository extends JpaRepository<PrivateJet, UUID> {

    List<Flight> findByOwner_Id(UUID jet_Id);

    List<Flight> findByModel(String model);

    List<Flight> findByCapacity(int capacity);

}
