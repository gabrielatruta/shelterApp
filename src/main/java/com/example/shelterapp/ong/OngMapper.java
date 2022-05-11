package com.example.shelterapp.ong;

import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OngMapper {

    OngDTO toDTO(Ong ong);

    @Mapping(target = "animals", ignore = true)
    Ong fromDTO(OngDTO ongDTO);

    OngMapper INSTANCE = Mappers.getMapper(OngMapper.class);

}
