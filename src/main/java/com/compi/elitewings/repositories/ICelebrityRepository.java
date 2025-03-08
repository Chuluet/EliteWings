package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICelebrityRepository extends JpaRepository<Celebrity, UUID> {
    List<Celebrity> findByName(String name);
    List<Celebrity> findByProfession(String profession);
    List<Celebrity> findByNetWorth(double netWorth);
    List<Celebrity> findBySuspiciousActivity(boolean suspiciousActivity);
}
