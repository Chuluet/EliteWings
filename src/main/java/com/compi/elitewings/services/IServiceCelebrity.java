package com.compi.elitewings.services;

import com.compi.elitewings.models.Celebrity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IServiceCelebrity {
    public List<Celebrity> getCelebrity();

    public void addCelebrity(Celebrity celebrity);

    public Optional<Celebrity> getCelebrityById(UUID id);

    public List<Celebrity> getCelebrityByName(String name);

    public List<Celebrity> getCelebrityByProfession(String profession);

    public List<Celebrity> getCelebrityByWorth(double worth);

    public List<Celebrity> getCelebrityBySuspiciousActivity(boolean suspiciousActivity);

    public void deleteCelebrity(UUID id);

    public void updateCelebrity(UUID id, Celebrity celebrity);
}
