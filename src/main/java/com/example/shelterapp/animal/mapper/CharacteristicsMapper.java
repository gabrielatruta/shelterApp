package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Characteristics;
import com.example.shelterapp.animal.model.dto.CharacteristicsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacteristicsMapper {

    Characteristics fromDTO(CharacteristicsDTO characteristicsDTO);

    CharacteristicsDTO toDTO(Characteristics characteristics);

    CharacteristicsMapper INSTANCE = Mappers.getMapper(CharacteristicsMapper.class);

}
