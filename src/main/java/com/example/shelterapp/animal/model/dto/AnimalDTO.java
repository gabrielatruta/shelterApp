package com.example.shelterapp.animal.model.dto;

import com.example.shelterapp.animal.model.enums.ESize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String species;
    private String name;
    private Float age;
    private String gender;
    private ESize size;
//    private Set<String> colorList = new HashSet<>();
//    private Set<String> characteristics = new HashSet<>();
    private Boolean neutered;
    private String picture;
    private String description;

}
