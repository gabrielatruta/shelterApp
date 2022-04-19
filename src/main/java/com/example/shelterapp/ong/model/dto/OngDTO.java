package com.example.shelterapp.ong.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OngDTO {

    private Long id;
    private String name;
    private String city;
    private String phoneNumber;
    private String description;
    private String website;

}
