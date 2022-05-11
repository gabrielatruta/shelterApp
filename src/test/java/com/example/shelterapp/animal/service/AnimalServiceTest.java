package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.repository.AnimalRepository;
import com.example.shelterapp.animal.repository.CharacteristicsRepository;
import com.example.shelterapp.animal.repository.ColorRepository;
import com.example.shelterapp.ong.OngMapper;
import com.example.shelterapp.ong.OngRepository;
import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
@TestPropertySource(locations = "/application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimalServiceTest {

    private final PodamFactory podamFactory = new PodamFactoryImpl();

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private OngRepository ongRepository;

    @Mock
    private CharacteristicsRepository characteristicsRepository;

    @Mock
    private ColorRepository colorRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        animalService = new AnimalService(animalRepository, characteristicsRepository, colorRepository);

        animalService.deleteAll();
        ongRepository.deleteAll();

        for (int i = 0; i < 100; i++) {
            Ong ongInstance = OngMapper.INSTANCE.fromDTO(podamFactory.manufacturePojo(OngDTO.class));
            Animal animalInstance = AnimalMapper.INSTANCE.fromDTO(podamFactory.manufacturePojo(AnimalDTO.class));
            ongInstance.addAnimal(animalInstance);
            ongRepository.save(ongInstance);
        }

    }

    @Test
    void allAnimals() {
        assertEquals(100, animalService.allAnimals().size());
    }

    @Test
    void createAnimal() {
        assertNotNull(animalService.createAnimal(podamFactory.manufacturePojo(AnimalDTO.class)));
    }

    @Test
    void edit() {
        AnimalDTO animal = animalService.get(66L);
        animal.setAge(14F);
        AnimalDTO editedAnimal = animalService.edit(animal.getId(), animal);
        assertEquals(animal, editedAnimal);
    }

    @Test
    void deleteAnimalById() {
        AnimalDTO animalToBeDeleted = animalService.get(53L);
        animalService.deleteAnimalById(53L);
        assertNotEquals(animalService.get(53L), animalToBeDeleted);
    }

    @Test
    void deleteAll() {
        animalService.deleteAll();
        assertEquals(0, animalService.allAnimals().size());
    }

    @Test
    void get() {
        assertNotNull(animalService.get(33L));
    }
}