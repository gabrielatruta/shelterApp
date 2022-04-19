package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Characteristics;
import com.example.shelterapp.animal.model.dto.CharacteristicsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacteristicsMapper {

    Characteristics fromDTO(CharacteristicsDTO characteristicsDTO);

    CharacteristicsDTO toDTO(Characteristics characteristics);

}
