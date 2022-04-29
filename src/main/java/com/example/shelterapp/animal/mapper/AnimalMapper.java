package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    @Mappings({
            @Mapping(target = "species", source = "species.name"),
            @Mapping(target = "size", source = "size.name"),
            @Mapping(target = "colorList", ignore = true),
            @Mapping(target = "characteristics", ignore = true),
            @Mapping(target = "ong", source = "ong.name")
    })
    AnimalDTO toDTO(Animal animal);


    @Mappings({
            @Mapping(target = "colorList", ignore = true),
            @Mapping(target = "characteristics", ignore = true),
            @Mapping(target = "ong", ignore = true)
    })
    Animal fromDTO(AnimalDTO animalDTO);

    /*
    mergeDTO -> Animal rez final, AnimalDTO + animal param
     */

    //Animal mergeDTO(AnimalDTO animalDTO, Animal animal);

    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

}
