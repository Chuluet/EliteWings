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

    List<PrivateJet> findByOwnerId(UUID ownerId);

    List<PrivateJet> findByModel(String model);

    List<PrivateJet> findByCapacity(Integer capacity);

}
