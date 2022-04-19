package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.SpeciesMapper;
import com.example.shelterapp.animal.model.Species;
import com.example.shelterapp.animal.model.dto.SpeciesDTO;
import com.example.shelterapp.animal.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesMapper speciesMapper;

    public List<SpeciesDTO> allSpecies() {
        return speciesRepository.findAll().stream()
                .map(speciesMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Species findById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no species with id = " + id));
    }

    public SpeciesDTO createSpecies(SpeciesDTO speciesDTO) {
        Species newSpecies = speciesMapper.fromDTO(speciesDTO);
        List<String> allSpecies = speciesRepository.findAll().stream()
                .map(x -> x.getName().getName())
                .collect(Collectors.toList());
        if (!allSpecies.contains(speciesDTO.getSpecies()))
            return speciesMapper.toDTO(speciesRepository.save(newSpecies));
        else
            try {
                throw new InstanceAlreadyExistsException(speciesDTO.getSpecies() + " species already exists!");
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            }
        return null;
    }

    public void deleteSpeciesById(Long id) {
        speciesRepository.deleteById(id);
    }

    public void deleteAllSpecies() {
        speciesRepository.deleteAll();
    }

    public SpeciesDTO getSpecies(Long id) {
        return speciesMapper.toDTO(findById(id));
    }
}
