package com.compi.elitewings.services;



import com.compi.elitewings.models.PrivateJet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IServicePrivateJet {
    public List<PrivateJet> getPrivateJet();

    public void addPrivateJet(PrivateJet privateJet);

    public Optional<PrivateJet> getPrivateJetById(UUID id);

    public List<PrivateJet> getPrivateJetByModel(String model);

    public List<PrivateJet> getPrivateJetByCapacity(int capacity);

    public Optional<PrivateJet> getPrivateJetByPilot(UUID pilotId);

}
