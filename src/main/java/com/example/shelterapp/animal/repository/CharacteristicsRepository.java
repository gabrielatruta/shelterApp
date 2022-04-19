package com.example.shelterapp.animal.repository;

import com.example.shelterapp.animal.model.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristics, Long> {

}
