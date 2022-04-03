package com.example.shelterapp.animal.model;

import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.animal.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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
    private LocalDate birthday;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ESize size;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "animal")
    private List<Color> colorList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "animal")
    private List<Characteristics> characteristics = new ArrayList<>();

    @Column(nullable = false)
    private Boolean neutered;

    @Column(nullable = false)
    private LocalDate dateOfLastVaccine;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ong ong;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && species == animal.species
                && Objects.equals(name, animal.name) && Objects.equals(age, animal.age)
                && Objects.equals(gender, animal.gender) && size == animal.size
                && Objects.equals(neutered, animal.neutered) && Objects.equals(ong, animal.ong)
                && Objects.equals(picture, animal.picture) && Objects.equals(description, animal.description);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
