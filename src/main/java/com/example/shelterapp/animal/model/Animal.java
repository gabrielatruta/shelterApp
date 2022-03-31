package com.example.shelterapp.animal.model;

import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.animal.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ESpecies species;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float age;

    @Column(nullable = false)
    private EGender gender;

    @Column(nullable = false)
    private ESize size;

//    @Column(nullable = false)
//    private Set<EColor> colorList = new HashSet<>();
//
//    @Column(nullable = false)
//    private Set<ECharacteristics> characteristics = new HashSet<>();

    @Column(nullable = false)
    private Boolean neutered;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String description;

}
