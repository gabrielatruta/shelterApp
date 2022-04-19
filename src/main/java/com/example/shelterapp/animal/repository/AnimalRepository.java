package com.example.shelterapp.animal.repository;

import com.example.shelterapp.animal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.ong.id = ?1")
    List<Animal> findAllAnimalsFromONG(Long id);

    @Query("SELECT a FROM Animal a WHERE a.age < ?1")
    List<Animal> findAllAnimalsBelowAge(Float age);

    /*
    contains
    like, notequal
    birthday for animal
    last vaccine, period for valid vax, what animals need new vaccines
     */
    @Query("SELECT a FROM Animal a WHERE a.description LIKE '?%1%'")
    List<Animal> findAllAnimalsByKeyword(String s1);

    @Query("SELECT a FROM Animal a WHERE a.neutered <> TRUE")
    List<Animal> findAllAnimalsNotNeutered();

    List<Animal> findAnimalsByDescriptionContains(String keyword);

}
