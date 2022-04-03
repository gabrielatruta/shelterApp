package com.example.shelterapp;

import com.example.shelterapp.animal.model.*;
import com.example.shelterapp.animal.model.enums.ECharacteristics;
import com.example.shelterapp.animal.model.enums.EColor;
import com.example.shelterapp.animal.model.enums.ESize;
import com.example.shelterapp.animal.model.enums.ESpecies;
import com.example.shelterapp.animal.repository.*;
import com.example.shelterapp.ong.OngRepository;
import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.user.model.ERole;
import com.example.shelterapp.user.model.Role;
import com.example.shelterapp.user.repository.RoleRepository;
import com.example.shelterapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.example.shelterapp.animal.model.enums.ESize.MEDIUM;
import static com.example.shelterapp.animal.model.enums.ESpecies.DOG;
import static java.lang.Boolean.TRUE;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final OngRepository ongRepository;
    private final SpeciesRepository speciesRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            animalRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            ongRepository.deleteAll();
            //TODO: saveAll

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
            for (ECharacteristics value : ECharacteristics.values()) {
                characteristicsRepository.save(
                        Characteristics.builder()
                                .characteristics(value)
                                .build()
                );
            }
            for (EColor value : EColor.values()) {
                colorRepository.save(
                        Color.builder()
                                .color(value)
                                .build()
                );
            }
            for (ESize value : ESize.values()) {
                sizeRepository.save(
                        Size.builder()
                                .size(value)
                                .build()
                );
            }

//            if (roleRepository.findByName(ADMINISTRATOR).isPresent()) {
//                User admin = User.builder()
//                        .email("admin@email.com")
//                        .username("admin")
//                        .password("Administrator3!")
//                        .roles(Set.of(roleRepository.findByName(ADMINISTRATOR).get()))
//                        .build();
//                userRepository.save(admin);
//            }
            Animal iris = Animal.builder()
                    .species(DOG)
                    .name("Iris")
                    .age(7F)
                    .gender("female")
                    .size(MEDIUM)
                    .neutered(TRUE)
                    .picture("test")
                    .description("German shepard mixture, very smart")
                    .build();
            animalRepository.save(iris);

            Ong randomONG = Ong.builder()
                    .name("Test ONG")
                    .description("test")
                    .city("Zalau")
                    .phoneNumber("test")
                    .website("test")
                    .build();

            randomONG.addAnimal(iris);
            animalRepository.save(iris);
            ongRepository.save(randomONG);


//            animalRepository.save(Animal.builder()
//                    .species(CAT)
//                    .name("Fonfi")
//                    .age(12F)
//                    .gender("male")
//                    .size(SMALL)
//                    .neutered(TRUE)
//                    .ong(randomONG)
//                    .picture("test")
//                    .description("Friendly cat, chubby, gets along well with any cat that doesn't start a fight")
//                    .build());

            /*
            REST assured pt teste
             */

        }
    }
}
