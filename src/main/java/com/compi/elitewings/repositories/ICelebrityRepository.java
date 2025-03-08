package com.compi.elitewings.repositories;

import com.compi.elitewings.models.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ICelebrityRepository extends JpaRepository<Celebrity, UUID> {
    List<Celebrity> findByCelebrity_Id(UUID celebrity_Id);
    List<Celebrity> findByName(String name);
    List<Celebrity> findByProfession(String profession);
    List<Celebrity> findByNet_worth(int net_worth);
    List<Celebrity> findBySuspicious_activity(boolean suspicious_activity);
}
