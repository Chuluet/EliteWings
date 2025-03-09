package com.compi.elitewings.controllers;

import com.compi.elitewings.models.Celebrity;
import com.compi.elitewings.services.IServiceCelebrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/celebrities")
public class CelebritiesController {
    private final IServiceCelebrity serviceCelebrity;

    @Autowired
    public CelebritiesController(IServiceCelebrity serviceCelebrity) {
        this.serviceCelebrity = serviceCelebrity;
    }

    @GetMapping("/")
    public List<Celebrity> index() {
        return this.serviceCelebrity.getCelebrity();
    }

    @PostMapping("/")
    public Celebrity add(@RequestBody Celebrity celebrity) {
        this.serviceCelebrity.addCelebrity(celebrity);
        return celebrity;
    }

    @GetMapping("/buscar/{id}")
    public Celebrity buscar(@PathVariable UUID id) {
        return this.serviceCelebrity.getCelebrityById(id)
                .orElse(null);
    }

    @GetMapping("/buscarNombre/{nombre}")
    public List<Celebrity> buscarNombre(@PathVariable String nombre) {
        return this.serviceCelebrity.getCelebrityByName(nombre);
    }

    @GetMapping("/buscarSospechosas/{sospechosa}")
    public List<Celebrity> buscarSospechosa(@PathVariable boolean sospechosa) {
        return this.serviceCelebrity.getCelebrityBySuspiciousActivity(sospechosa);
    }
}
