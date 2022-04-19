package com.example.shelterapp.animal.mapper;

import com.example.shelterapp.animal.model.Size;
import com.example.shelterapp.animal.model.dto.SizeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    Size fromDTO(SizeDTO sizeDTO);

    SizeDTO toDTO(Size size);

}
