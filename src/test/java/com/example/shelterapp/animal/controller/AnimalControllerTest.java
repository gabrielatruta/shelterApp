package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.mapper.AnimalMapper;
import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class AnimalControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private AnimalController animalController;

    private final PodamFactory factory = new PodamFactoryImpl();

    @Autowired
    private AnimalRepository animalR;

    @BeforeEach
    protected void setUp() {

//        List<AnimalDTO> animals = factory.manufacturePojo(ArrayList.class, AnimalDTO.class);
//        when(animalService.get(anyLong())).thenReturn(animals);


        for(int i = 0; i < 100; i++){
            AnimalDTO animal = factory.manufacturePojo(AnimalDTO.class);
            animalR.save(AnimalMapper.INSTANCE.fromDTO(animal));
        }
    }

    @Test
    void allAnimals() {

        ResponseEntity<List<AnimalDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/api/animal", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AnimalDTO>>(){});
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<AnimalDTO> list = responseEntity.getBody();
        assertEquals(100, list.size());
    }

    @Test
    void createAnimal() {
        AnimalDTO animalDTO =  factory.manufacturePojo(AnimalDTO.class);
        ResponseEntity<AnimalDTO> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/api/animal",
                animalDTO, AnimalDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertTrue(Objects.nonNull(responseEntity.getBody()));
        assertEquals(animalDTO.getAge(), responseEntity.getBody().getAge());
    }

    @Test
    void deleteAnimalById() {
        animalController.deleteAnimalById(33L);

    }

    @Test
    void deleteAllAnimals() {
        animalController.deleteAllAnimals();

    }

    @Test
    void editAnimal() {
       AnimalDTO animalDTO = animalController.getAnimal(56L);
       animalDTO.setAge(14F);
       animalDTO.setGender("female");
       AnimalDTO afterChanges = animalController.editAnimal(56L, animalDTO);
       assertEquals(animalDTO, afterChanges);
    }

    @Test
    void getAnimal() {
        assertNotNull(animalController.getAnimal(78L));
    }
}