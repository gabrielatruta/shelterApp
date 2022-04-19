package com.example.shelterapp.animal.model;

import com.example.shelterapp.animal.model.enums.ESize;
import com.example.shelterapp.animal.model.enums.ESpecies;
import com.example.shelterapp.ong.model.Ong;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private List<Color> colorList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "animal")
    @ToString.Exclude
    private List<Characteristics> characteristics = new ArrayList<>();

    @Column(nullable = false)
    private Boolean neutered;

    @Column(nullable = false)
    private LocalDate dateOfLastVaccine;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
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

    public void addToOng (Ong ong) {
        this.ong = ong;
    }
}
