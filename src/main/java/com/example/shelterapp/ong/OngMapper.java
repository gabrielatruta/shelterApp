package com.example.shelterapp.ong;

import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OngMapper {

    OngDTO toDTO(Ong ong);
    Ong fromDTO(OngDTO ongDTO);

}
