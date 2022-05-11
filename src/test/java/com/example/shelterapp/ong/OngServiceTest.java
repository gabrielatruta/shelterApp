package com.example.shelterapp.ong;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.repository.AnimalRepository;
import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
class OngServiceTest {

    private final PodamFactory podamFactory = new PodamFactoryImpl();

    @InjectMocks
    private OngService ongService;

    @Mock
    private OngRepository ongRepository;

    @Mock
    private AnimalRepository animalRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ongService = new OngService(ongRepository, animalRepository);
        ongService.deleteAll();
        for (int i = 0; i < 100; i++)
            ongService.createOng(podamFactory.manufacturePojo(OngDTO.class));
    }

    @Test
    void findAll() {
        assertEquals(100, ongService.findAll().size());
    }

    @Test
    void createOng() {
        assertNotNull(ongService.createOng(podamFactory.manufacturePojo(OngDTO.class)));
    }

    @Test
    void editOng() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void get() {
    }
}