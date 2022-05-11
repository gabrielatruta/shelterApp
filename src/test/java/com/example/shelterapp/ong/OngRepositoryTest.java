package com.example.shelterapp.ong;

import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
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
class OngRepositoryTest {

    private final PodamFactory podamFactory = new PodamFactoryImpl();

    @Autowired
    private OngRepository ongRepository;

    @Test
    void findOngByName() {

        Ong ongInstance = OngMapper.INSTANCE.fromDTO(podamFactory.manufacturePojo(OngDTO.class));
        ongInstance.setName("Arca lui Noe");
        ongRepository.save(ongInstance);
        assertNotNull(ongRepository.findOngByName("Arca lui Noe"));

    }
}