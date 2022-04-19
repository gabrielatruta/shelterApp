package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.Characteristics;
import com.example.shelterapp.animal.model.Color;
import com.example.shelterapp.animal.model.Size;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.repository.*;
import com.example.shelterapp.ong.OngRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final SpeciesRepository speciesRepository;
    private final OngRepository ongRepository;
    private final AnimalMapper animalMapper;

    public List<AnimalDTO> allAnimals() {

        return animalRepository.findAll().stream()
                .map(AnimalMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    private Animal findAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No animal with id = " + id + " could be found!"));
    }

    private Optional<Size> findSizeByName(String name) {
        List<Size> sizes = sizeRepository.findAll();
        for (Size size : sizes) {
            if (Objects.equals(size.getSize().toString(), name))
                return Optional.of(size);
        }
        return Optional.empty();
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
        animal.setSpecies(speciesRepository.findSpeciesByName(animalDTO.getSpecies()));
        animal.setName(animalDTO.getName());
        animal.setAge(animalDTO.getAge());
        animal.setBirthday(LocalDate.parse(animalDTO.getBirthday()));
        animal.setGender(animalDTO.getGender());
        if (findSizeByName(animalDTO.getSize()).isPresent())
            animal.setSize(findSizeByName(animalDTO.getSize()).get().getSize());
        else
            throw new EntityNotFoundException("Couldn't find size = " + animalDTO.getSize());
        animal.setColorList(findColorsFromStringList(animalDTO.getColorList()));
        animal.setCharacteristics(findCharacteristicsFromStringList(animalDTO.getCharacteristics()));
        animal.setNeutered(animalDTO.getNeutered());
        animal.setDateOfLastVaccine(LocalDate.parse(animalDTO.getDateOfLastVaccine()));
        if (ongRepository.findOngByName(animalDTO.getOng()).isPresent())
            animal.setOng(ongRepository.findOngByName(animalDTO.getOng()).get());
        else
            throw new EntityNotFoundException("Couldn't find ong with name = " + animalDTO.getOng());
        animal.setPicture(animalDTO.getPicture());
        animal.setDescription(animalDTO.getDescription());

        return animalMapper.toDTO(animalRepository.save(animal));
    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = animalMapper.fromDTO(animalDTO);
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
        return animalMapper.toDTO(findAnimalById(id));
    }
}
