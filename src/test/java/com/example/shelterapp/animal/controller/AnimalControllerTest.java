package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.model.dto.AnimalDTO;
import com.example.shelterapp.animal.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AnimalControllerTest {

    @InjectMocks
    private AnimalController animalController;

    private final PodamFactory factory = new PodamFactoryImpl();

    @Mock
    private AnimalService animalService;

    @BeforeEach
    protected void setUp() {
        animalController = new AnimalController(animalService);
//        List<AnimalDTO> animals = factory.manufacturePojo(ArrayList.class, AnimalDTO.class);
//        when(animalService.get(anyLong())).thenReturn(animals);


        for(int i = 0; i < 100; i++){
            AnimalDTO animal = factory.manufacturePojo(AnimalDTO.class);
            animalController.createAnimal((AnimalDTO) when(animalService.get(anyLong())).thenReturn(animal));
        }
    }

    @Test
    void allAnimals() {
        assertEquals(100, animalController.allAnimals().size());
    }

    @Test
    void createAnimal() {
    }

    @Test
    void deleteAnimalById() {
        animalController.deleteAnimalById(33L);
        assertEquals(99, animalController.allAnimals().size());
    }

    @Test
    void deleteAllAnimals() {
        animalController.deleteAllAnimals();
        assertEquals(0, animalController.allAnimals().size());
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