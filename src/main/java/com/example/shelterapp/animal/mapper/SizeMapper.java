package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Size;
import com.example.shelterapp.animal.model.dto.SizeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "animal", ignore = true)
    })
    Size fromDTO(SizeDTO sizeDTO);

    SizeDTO toDTO(Size size);

    SizeMapper INSTANCE = Mappers.getMapper(SizeMapper.class);

}
