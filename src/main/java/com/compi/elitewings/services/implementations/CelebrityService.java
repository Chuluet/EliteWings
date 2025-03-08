package com.compi.elitewings.services.implementations;


import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.repositories.ICelebrityRepository;
import com.compi.elitewings.services.IServiceCelebrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CelebrityService implements IServiceCelebrity {
    private final ICelebrityRepository celebrityRepository;

    @Autowired
    public CelebrityService(ICelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }

    @Override
    public List<Celebrity> getCelebrity() {
        return this.celebrityRepository.findAll();
    }

    @Override
    public void addCelebrity(Celebrity celebrity) {
        this.celebrityRepository.save(celebrity);
    }

    @Override
    public Optional<Celebrity> getCelebrityById(UUID id) {
        return this.celebrityRepository.findById(id);
    }

    @Override
    public Optional<Celebrity> getCelebrityByName(String name) {
        return this.celebrityRepository.findByName(name);
    }

    @Override
    public List<Celebrity> getCelebrityByProfession(String profession) {
        return this.celebrityRepository.findByProfession(profession);
    }

    @Override
    public List<Celebrity> getCelebrityByWorth(double worth) {
        return this.celebrityRepository.findByNet_worth(worth);
    }

    @Override
    public List<Celebrity> getCelebrityBySuspiciousActivity(boolean suspiciousActivity) {
        return this.celebrityRepository.findBySuspicious_activity(suspiciousActivity);
    }
}
