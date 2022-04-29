package com.example.shelterapp.animal.model.dto;

import com.example.shelterapp.utils.StringifyLocalDate;
import com.example.shelterapp.utils.StringifySize;
import com.example.shelterapp.utils.StringifySpecies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    @PodamStrategyValue(StringifySpecies.class)
    private String species;
    private String name;
    private Float age;
    @PodamStrategyValue(StringifyLocalDate.class)
    private String birthday;
    private String gender;
    @PodamStrategyValue(StringifySize.class)
    private String size;
    @Builder.Default
    private List<String> colorList = new ArrayList<>();
    @Builder.Default
    private List<String> characteristics = new ArrayList<>();
    private Boolean neutered;
    @PodamStrategyValue(StringifyLocalDate.class)
    private String dateOfLastVaccine;
    private String ong;
    private String picture;
    private String description;

}
