package com.example.shelterapp.animal;

import com.example.shelterapp.animal.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

//    public List<AnimalDTO> allAnimals() {
//
//    }

}
