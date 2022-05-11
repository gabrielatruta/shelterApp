package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Color;
import com.example.shelterapp.animal.model.dto.ColorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "animal", ignore = true)
    })
    Color fromDTO(ColorDTO colorDTO);

    ColorDTO toDTO(Color color);

    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

}
