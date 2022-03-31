package com.example.shelterapp;

import com.example.shelterapp.animal.AnimalRepository;
import com.example.shelterapp.animal.SpeciesRepository;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.Species;
import com.example.shelterapp.animal.model.enums.ESpecies;
import com.example.shelterapp.ong.OngRepository;
import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.user.model.ERole;
import com.example.shelterapp.user.model.Role;
import com.example.shelterapp.user.model.User;
import com.example.shelterapp.user.repository.RoleRepository;
import com.example.shelterapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.example.shelterapp.animal.model.enums.EGender.FEMALE;
import static com.example.shelterapp.animal.model.enums.EGender.MALE;
import static com.example.shelterapp.animal.model.enums.ESize.MEDIUM;
import static com.example.shelterapp.animal.model.enums.ESize.SMALL;
import static com.example.shelterapp.animal.model.enums.ESpecies.CAT;
import static com.example.shelterapp.animal.model.enums.ESpecies.DOG;
import static com.example.shelterapp.user.model.ERole.ADMINISTRATOR;
import static java.lang.Boolean.TRUE;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final OngRepository ongRepository;
    private final SpeciesRepository speciesRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            animalRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            ongRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            for (ESpecies value : ESpecies.values()) {
                speciesRepository.save(
                        Species.builder()
                                .name(value)
                                .build()
                );
            }
            Role adminRole = Role.builder()
                    .name(ADMINISTRATOR)
                    .build();

            User admin = User.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("Administrator3!")
                    .roles(Set.of(adminRole))
                    .build();
            userRepository.save(admin);

            Ong randomONG = Ong.builder()
                    .name("Test ONG")
                    .description("test")
                    .city("Zalau")
                    .phoneNumber("test")
                    .website("test")
                    .build();

            Animal Iris = Animal.builder()
                    .species(DOG)
                    .name("Iris")
                    .age(7F)
                    .gender(FEMALE)
                    .size(MEDIUM)
                    .neutered(TRUE)
                    .ong(randomONG)
                    .picture("test")
                    .description("German shepard mixture, very smart")
                    .build();
            animalRepository.save(Iris);

            animalRepository.save(Animal.builder()
                    .species(CAT)
                    .name("Fonfi")
                    .age(12F)
                    .gender(MALE)
                    .size(SMALL)
                    .neutered(TRUE)
                    .ong(randomONG)
                    .picture("test")
                    .description("Friendly cat, chubby, gets along well with any cat that doesn't start a fight")
                    .build());


        }
    }
}
