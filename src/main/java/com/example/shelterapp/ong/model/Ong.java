package com.example.shelterapp.ong.model;

import com.example.shelterapp.animal.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String website;

    @OneToMany(
            mappedBy = "ong",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<Animal> animals = new ArrayList<>();

    public void addAnimal (Animal animal) {
        animals.add(animal);
        animal.setOng(this);

    }

    public void removeAnimal (Animal animal) {
        animals.remove(animal);
        animal.setOng(null);
    }

}
