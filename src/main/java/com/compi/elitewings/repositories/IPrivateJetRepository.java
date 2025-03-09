package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.models.Flight;
import com.compi.elitewings.models.PrivateJet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPrivateJetRepository extends JpaRepository<PrivateJet, UUID> {

    Optional<PrivateJet> findByPilotId(UUID jet_Id);

    List<PrivateJet> findByModel(String model);

    List<PrivateJet> findByCapacity(int capacity);

}
