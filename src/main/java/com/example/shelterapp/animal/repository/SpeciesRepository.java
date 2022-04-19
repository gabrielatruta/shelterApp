package com.example.shelterapp.animal.repository;

import com.example.shelterapp.animal.model.Species;
import com.example.shelterapp.animal.model.enums.ESpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    @Query("SELECT s FROM Species s WHERE s.name = ?1")
    ESpecies findSpeciesByName(String species);

}
