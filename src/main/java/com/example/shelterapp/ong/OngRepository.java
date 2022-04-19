package com.example.shelterapp.ong;

import com.example.shelterapp.ong.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long>{

    Optional<Ong> findOngByName(String name);
}
