package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Size;
import com.example.shelterapp.animal.model.dto.SizeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    Size fromDTO(SizeDTO sizeDTO);

    SizeDTO toDTO(Size size);

    SizeMapper INSTANCE = Mappers.getMapper(SizeMapper.class);

}
