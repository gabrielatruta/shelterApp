package com.example.shelterapp.animal.repository;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.ong.OngMapper;
import com.example.shelterapp.ong.OngRepository;
import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        for (int i = 0; i < 100; i++) {
            Ong ongInstance = OngMapper.INSTANCE.fromDTO(podamFactory.manufacturePojo(OngDTO.class));
            Animal animalInstance = AnimalMapper.INSTANCE.fromDTO(podamFactory.manufacturePojo(AnimalDTO.class));
            ongInstance.addAnimal(animalInstance);
            ongRepository.save(ongInstance);
        }

    }


    @Test
    void findAllAnimalsFromONG() {
        assertNotNull(animalRepository.findAllAnimalsFromONG(ongRepository.getOne(1L).getId()));
    }

    @Test
    void findAllAnimalsBelowAge() {
        assertNotNull(animalRepository.findAllAnimalsBelowAge(3F));
    }

    @Test
    void findAllAnimalsByKeyword() {
        assertNotNull(animalRepository.findAllAnimalsByDescriptionKeyword("as"));
    }

    @Test
    void findAllAnimalsNotNeutered() {
        assertNotNull(animalRepository.findAllAnimalsNotNeutered());
    }

    @Test
    void findAnimalsByDescriptionContains() {
        assertNotNull(animalRepository.findAnimalsByDescriptionContains("as"));
    }
}