package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.service.SpeciesService;
import com.example.shelterapp.animal.model.dto.SpeciesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shelterapp.utils.URLMapping.ENTITY;
import static com.example.shelterapp.utils.URLMapping.SPECIES;

@RestController
@RequestMapping(SPECIES)
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;

    @GetMapping
    public List<SpeciesDTO> allSpecies() {
        return speciesService.allSpecies();
    }

    @PostMapping
    public SpeciesDTO createSpecies(@RequestBody SpeciesDTO speciesDTO) {
        return speciesService.createSpecies(speciesDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteSpeciesById(@PathVariable Long id) {
        speciesService.deleteSpeciesById(id);
    }

    @DeleteMapping
    public void deleteAllSpecies() {
        speciesService.deleteAllSpecies();
    }

    @GetMapping(ENTITY)
    public SpeciesDTO getSpecies(@PathVariable Long id) {
        return speciesService.getSpecies(id);
    }
}
