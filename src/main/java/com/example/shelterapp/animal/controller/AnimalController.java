package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.service.AnimalService;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shelterapp.utils.URLMapping.ANIMAL;
import static com.example.shelterapp.utils.URLMapping.ENTITY;

@RestController
@RequestMapping(ANIMAL)
@RequiredArgsConstructor
@CrossOrigin
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> allAnimals() {
        return ResponseEntity.ok(animalService.allAnimals());
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> createAnimal(@RequestBody AnimalDTO animalDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.createAnimal(animalDTO));
    }

    @DeleteMapping(ENTITY)
    public void deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
    }

    @DeleteMapping
    public void deleteAllAnimals() {
        animalService.deleteAll();
    }

    @PatchMapping(ENTITY)
    public AnimalDTO editAnimal(@PathVariable Long id, @RequestBody AnimalDTO animalDTO) {
        return animalService.edit(id, animalDTO);
    }

    @GetMapping(ENTITY)
    public AnimalDTO getAnimal(@PathVariable Long id) {
        return animalService.get(id);
    }

}
