package com.example.shelterapp.animal.repository;

import com.example.shelterapp.animal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.ong.id = ?1")
    Collection<Animal> findAllAnimalsFromONG(Long id);

    @Query("SELECT a FROM Animal a WHERE a.age < ?1")
    Collection<Animal> findAllAnimalsBelowAge(Float age);
}
