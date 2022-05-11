package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Species;
import com.example.shelterapp.animal.model.dto.SpeciesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "animal", ignore = true),
            @Mapping(target = "name", source = "species")
    })
    Species fromDTO(SpeciesDTO speciesDTO);

    @Mapping(target = "species", source = "name.name")
    SpeciesDTO toDTO(Species species);

    SpeciesMapper INSTANCE = Mappers.getMapper(SpeciesMapper.class);

}
