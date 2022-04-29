package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Color;
import com.example.shelterapp.animal.model.dto.ColorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    Color fromDTO(ColorDTO colorDTO);

    ColorDTO toDTO(Color color);

    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

}
