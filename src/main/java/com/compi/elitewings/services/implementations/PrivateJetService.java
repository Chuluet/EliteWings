package com.compi.elitewings.services.implementations;

import com.compi.elitewings.models.PrivateJet;
import com.compi.elitewings.repositories.IPrivateJetRepository;
import com.compi.elitewings.services.IServicePrivateJet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrivateJetService implements IServicePrivateJet {
    private final IPrivateJetRepository privateJetRepository;

    @Autowired
    public PrivateJetService(IPrivateJetRepository privateJetRepository) {
        this.privateJetRepository = privateJetRepository;
    }
    @Override
    public List<PrivateJet> getPrivateJet() {
        return this.privateJetRepository.findAll();
    }

    @Override
    public void addPrivateJet(PrivateJet privateJet) {
        this.privateJetRepository.save(privateJet);
    }

    @Override
    public Optional<PrivateJet> getPrivateJetById(UUID id) {
        return this.privateJetRepository.findById(id);
    }

    @Override
    public List<PrivateJet> getPrivateJetByModel(String model) {
        return this.privateJetRepository.findByModel(model);
    }

    @Override
    public List<PrivateJet> getPrivateJetByCapacity(int capacity) {
        return this.privateJetRepository.findByCapacity(capacity);
    }

    @Override
    public Optional<PrivateJet> getPrivateJetByPilot(UUID pilotId) {
        return this.privateJetRepository.findByPilotId(pilotId);
    }
    @Override
    public void deletePrivateJet(UUID id) {
        this.privateJetRepository.deleteById(id);
    }

    @Override
    public void updatePrivateJet(UUID id, PrivateJet privateJet) {
        Optional<PrivateJet> existingPrivateJetOptional = this.privateJetRepository.findById(id);

        if (existingPrivateJetOptional.isPresent()) {
            PrivateJet existingPrivateJet = existingPrivateJetOptional.get();
            existingPrivateJet.setCapacity(privateJet.getCapacity());
            existingPrivateJet.setModel(privateJet.getModel());
            existingPrivateJet.setPilotId(privateJet.getPilotId());
            this.privateJetRepository.save(existingPrivateJet);
        } else {
            throw new RuntimeException("Private jet with ID " + id + " not found");
        }
    }
}
