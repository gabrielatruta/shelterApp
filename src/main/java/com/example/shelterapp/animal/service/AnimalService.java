package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.Characteristics;
import com.example.shelterapp.animal.model.Color;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.repository.AnimalRepository;
import com.example.shelterapp.animal.repository.CharacteristicsRepository;
import com.example.shelterapp.animal.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final ColorRepository colorRepository;

    public List<AnimalDTO> allAnimals() {

        return animalRepository.findAll().stream()
                .map(AnimalMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    private Animal findAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No animal with id = " + id + " could be found!"));
    }

    private List<Color> findColorsFromStringList(List<String> colors) {
        List<Color> allColors = colorRepository.findAll();
        List<Color> requiredColors = new ArrayList<>();
        for (String stringColor : colors) {
            for (Color color : allColors) {
                if (stringColor.equals(color.getColor().toString()))
                    requiredColors.add(color);
            }
        }
        return requiredColors;
    }

    private List<Characteristics> findCharacteristicsFromStringList(List<String> characteristics) {
        List<Characteristics> allCharacteristics = characteristicsRepository.findAll();
        List<Characteristics> requiredCharacteristics = new ArrayList<>();
        for (String stringCharacteristics : characteristics) {
            for (Characteristics characteristic : allCharacteristics) {
                if (stringCharacteristics.equals(characteristic.getCharacteristics().toString()))
                    requiredCharacteristics.add(characteristic);
            }
        }
        return requiredCharacteristics;
    }

    private AnimalDTO setAnimalFields(AnimalDTO animalDTO, Animal animal) {

        animal.setColorList(findColorsFromStringList(animalDTO.getColorList()));
        animal.setCharacteristics(findCharacteristicsFromStringList(animalDTO.getCharacteristics()));

        return AnimalMapper.INSTANCE.toDTO(animalRepository.save(animal));
    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = AnimalMapper.INSTANCE.fromDTO(animalDTO);
        return setAnimalFields(animalDTO, animal);
    }

    public AnimalDTO edit(Long id, AnimalDTO animalDTO) {
        Animal actAnimal = findAnimalById(id);
        return setAnimalFields(animalDTO, actAnimal);
    }

    public void deleteAnimalById(Long id) {
        animalRepository.deleteById(id);
    }

    public void deleteAll() {
        animalRepository.deleteAll();
    }

    public AnimalDTO get(Long id) {
        return AnimalMapper.INSTANCE.toDTO(findAnimalById(id));
    }
}
