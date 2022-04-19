package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.CharacteristicsMapper;
import com.example.shelterapp.animal.model.Characteristics;
import com.example.shelterapp.animal.model.dto.CharacteristicsDTO;
import com.example.shelterapp.animal.repository.CharacteristicsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacteristicsService {

    private final CharacteristicsRepository characteristicsRepository;
    private final CharacteristicsMapper characteristicsMapper;

    public List<CharacteristicsDTO> allCharacteristics() {
        return characteristicsRepository.findAll().stream()
                .map(characteristicsMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Characteristics findById(Long id) {
        return characteristicsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no characteristics with id = " + id));
    }

    public CharacteristicsDTO createCharacteristics(CharacteristicsDTO characteristicsDTO) {
        Characteristics characteristics = characteristicsMapper.fromDTO(characteristicsDTO);
        List<String> allCharacteristics = characteristicsRepository.findAll().stream()
                .map(x -> x.getCharacteristics().getName())
                .collect(Collectors.toList());
        if (!allCharacteristics.contains(characteristicsDTO.getCharacteristics()))
            return characteristicsMapper.toDTO(characteristicsRepository.save(characteristics));
        else
            try {
                throw new InstanceAlreadyExistsException(
                        characteristicsDTO.getCharacteristics() + " characteristics already exists!");
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            }
        return null;
    }

    public void deleteCharacteristics(Long id) {
        characteristicsRepository.deleteById(id);
    }

    public void deleteAllCharacteristics() {
        characteristicsRepository.deleteAll();
    }

    public CharacteristicsDTO getCharacteristics(Long id) {
        return characteristicsMapper.toDTO(findById(id));
    }

}
