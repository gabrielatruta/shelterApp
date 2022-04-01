package com.example.shelterapp.user.repository;

import com.example.shelterapp.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

//    @Query("SELECT u FROM  User u JOIN u.roles r WHERE  r.name LIKE ADMINISTRATOR")
//    Page<User> findAllAdmins();
}
