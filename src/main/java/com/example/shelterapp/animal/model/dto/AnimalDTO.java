package com.example.shelterapp.animal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String species;
    private String name;
    private Float age;
    private String birthday;
    private String gender;
    private String size;
    private List<String> colorList = new ArrayList<>();
    private List<String> characteristics = new ArrayList<>();
    private Boolean neutered;
    private String dateOfLastVaccine;
    private String ong;
    private String picture;
    private String description;

}
