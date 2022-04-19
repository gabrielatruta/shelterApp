package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.service.CharacteristicsService;
import com.example.shelterapp.animal.model.dto.CharacteristicsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shelterapp.URLMapping.CHARACTERISTICS;
import static com.example.shelterapp.URLMapping.ENTITY;

@RestController
@RequestMapping(CHARACTERISTICS)
@RequiredArgsConstructor
public class CharacteristicsController {

    private final CharacteristicsService characteristicsService;

    @GetMapping
    public List<CharacteristicsDTO> allCharacteristics() {
        return characteristicsService.allCharacteristics();
    }

    @PostMapping
    public CharacteristicsDTO createCharacteristics(@RequestBody CharacteristicsDTO characteristicsDTO) {
        return characteristicsService.createCharacteristics(characteristicsDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteCharacteristicsById(@PathVariable Long id) {
        characteristicsService.deleteCharacteristics(id);
    }

    @DeleteMapping
    public void deleteAllCharacteristics() {
        characteristicsService.deleteAllCharacteristics();
    }

    @GetMapping(ENTITY)
    public CharacteristicsDTO getCharacteristics(@PathVariable Long id) {
        return characteristicsService.getCharacteristics(id);
    }
}
