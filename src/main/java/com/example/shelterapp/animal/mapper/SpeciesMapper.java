package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Species;
import com.example.shelterapp.animal.model.dto.SpeciesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    Species fromDTO(SpeciesDTO speciesDTO);

    @Mapping(target = "species", source = "name.name")
    SpeciesDTO toDTO(Species species);

}
