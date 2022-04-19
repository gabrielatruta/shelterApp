package com.example.shelterapp.ong.model;

import com.example.shelterapp.animal.model.Animal;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
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
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    @JoinColumn(name = "ong_id")
    @ToString.Exclude
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal (Animal animal) {
        animals.add(animal);
        animal.setOng(this);
    }

    public void removeAnimal (Animal animal) {
        animals.remove(animal);
        animal.setOng(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ong ong = (Ong) o;
        return id != null && Objects.equals(id, ong.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
