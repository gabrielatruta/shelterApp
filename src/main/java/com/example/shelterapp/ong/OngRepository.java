package com.example.shelterapp.ong;

import com.example.shelterapp.ong.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long>{

}
