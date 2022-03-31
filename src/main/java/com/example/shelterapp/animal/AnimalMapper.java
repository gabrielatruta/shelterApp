package com.example.shelterapp.animal;

import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDTO toDTO(Animal animal);
    Animal fromDTO(AnimalDTO animalDTO);

}
