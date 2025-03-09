package com.compi.elitewings.controllers;

import com.compi.elitewings.models.PrivateJet;
import com.compi.elitewings.services.IServicePrivateJet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/privateJet")
public class PrivateJetController {
    private final IServicePrivateJet servicePrivateJet;

    @Autowired
    public PrivateJetController(IServicePrivateJet servicePrivateJet) {
        this.servicePrivateJet = servicePrivateJet;
    }

    @GetMapping("/")
    public List<PrivateJet> index() {
        return this.servicePrivateJet.getPrivateJet();
    }

    @PostMapping("/")
    public PrivateJet add(@RequestBody PrivateJet privateJet) {
        this.servicePrivateJet.addPrivateJet(privateJet);
        return privateJet;
    }

    @GetMapping("/buscar/{id}")
    public PrivateJet buscar(@PathVariable UUID id) {
        return this.servicePrivateJet.getPrivateJetById(id)
                .orElse(null);
    }

    @GetMapping("/buscarModelo/{modelo}")
    public List<PrivateJet> buscarModelo(@PathVariable String modelo) {
        return this.servicePrivateJet.getPrivateJetByModel(modelo);
    }
    @GetMapping("/buscarCapacidad/{capacidad}")
    public List<PrivateJet> buscarCapacidad(@PathVariable int capacidad) {
        return this.servicePrivateJet.getPrivateJetByCapacity(capacidad);
    }
    @GetMapping("/buscarPiloto/{pilotId}")
    public PrivateJet buscarPiloto(@PathVariable UUID pilotId) {
        return this.servicePrivateJet.getPrivateJetByPilot(pilotId)
                .orElse(null);
    }

}
