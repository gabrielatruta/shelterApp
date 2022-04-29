package com.example.shelterapp.animal.repository;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.model.enums.ESize;
import com.example.shelterapp.animal.model.enums.ESpecies;
import com.example.shelterapp.ong.OngRepository;
import com.example.shelterapp.ong.model.Ong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.inject.Inject;

import java.time.LocalDate;

import static com.example.shelterapp.animal.model.enums.ESize.MEDIUM;
import static com.example.shelterapp.animal.model.enums.ESpecies.DOG;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
@TestPropertySource(locations = "/application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimalRepositoryTest {

    private final PodamFactory podamFactory = new PodamFactoryImpl();

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private OngRepository ongRepository;

    @BeforeEach
    public void beforeEach() {
        animalRepository.deleteAll();
        ongRepository.deleteAll();
        Animal iris = animalRepository.save(Animal.builder()
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
                .build());

        Ong randomONG = ongRepository.save(Ong.builder()
                .name("Test ONG")
                .description("test")
                .city("Zalau")
                .phoneNumber("test")
                .website("test")
                .build());

        randomONG.addAnimal(iris);
        ongRepository.save(randomONG);

    }


    @Test
    void findAllAnimalsFromONG() {

        Ong ong = ongRepository.findOngByName("Test ONG").get();
        assertNotNull(animalRepository.findAllAnimalsFromONG(ong.getId()));

    }

    @Test
    void findAllAnimalsBelowAge() {
        AnimalDTO animalDTO = podamFactory.manufacturePojo(AnimalDTO.class);
        System.out.println("test: " + AnimalMapper.INSTANCE.fromDTO(animalDTO));
    }

    @Test
    void findAllAnimalsByKeyword() {
    }

    @Test
    void findAllAnimalsNotNeutered() {
    }

    @Test
    void findAnimalsByDescriptionContains() {
    }
}