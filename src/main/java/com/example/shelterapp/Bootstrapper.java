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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    //private final PodamFactory podamFactory = new PodamFactoryImpl();

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            animalRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            ongRepository.deleteAll();

            roleRepository.saveAll(Arrays.stream(ERole.values())
                    .map(x -> Role.builder()
                            .name(ERole.fromValue(x.getName()))
                            .build())
                    .collect(Collectors.toList()));

            speciesRepository.saveAll(Arrays.stream(ESpecies.values())
                    .map(x -> Species.builder()
                            .name(ESpecies.fromValue(x.getName()))
                            .build())
                    .collect(Collectors.toList()));

            characteristicsRepository.saveAll(Arrays.stream(ECharacteristics.values())
                    .map(x -> Characteristics.builder()
                            .characteristics(ECharacteristics.fromValue(x.getName()))
                            .build())
                    .collect(Collectors.toList()));

            colorRepository.saveAll(Arrays.stream(EColor.values())
                    .map(x -> Color.builder()
                            .color(EColor.fromValue(x.getName()))
                            .build())
                    .collect(Collectors.toList()));

            sizeRepository.saveAll(Arrays.stream(ESize.values())
                    .map(x -> Size.builder()
                            .size(ESize.fromValue(x.getName()))
                            .build())
                    .collect(Collectors.toList()));

//            for (int i = 0; i < 100; i++) {
//                Animal animal = podamFactory.manufacturePojo(Animal.class);
//                animalRepository.save(animal);
//            }


            Animal iris = Animal.builder()
                    .species(DOG)
                    .name("Iris")
                    .age(7F)
                    .birthday(LocalDate.now())
                    .gender("female")
                    .size(MEDIUM)
                    .neutered(TRUE)
                    .dateOfLastVaccine(LocalDate.now())
                    .picture("test")
                    .description("German shepard mixture, very smart")
                    .build();

            Ong randomONG = Ong.builder()
                    .name("Test ONG")
                    .description("test")
                    .city("Zalau")
                    .phoneNumber("test")
                    .website("test")
                    .build();
            randomONG.addAnimal(iris);
            ongRepository.save(randomONG);
//            iris.setOng(randomONG);
//            animalRepository.save(iris);

            /*
            REST assured pt teste
             */

        }
    }
}
