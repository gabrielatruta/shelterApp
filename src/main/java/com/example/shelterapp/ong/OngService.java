package com.example.shelterapp.ong;

import com.example.shelterapp.animal.model.Animal;
import com.example.shelterapp.animal.repository.AnimalRepository;
import com.example.shelterapp.ong.model.Ong;
import com.example.shelterapp.ong.model.dto.OngDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OngService {

    private final OngRepository ongRepository;
    private final AnimalRepository animalRepository;

    public List<OngDTO> findAll() {
        return ongRepository.findAll()
                .stream().map(OngMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    private Ong findById(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ong with id = " + id + " not found!"));
    }

    public OngDTO createOng(OngDTO ongDTO) {
        Ong ong = OngMapper.INSTANCE.fromDTO(ongDTO);
        return OngMapper.INSTANCE.toDTO(ongRepository.save(ong));
    }

    public OngDTO editOng(Long id, OngDTO ongDTO) {
        Ong actOng = OngMapper.INSTANCE.fromDTO(ongDTO);
        List<Animal> allAnimalsFromONG = animalRepository.findAllAnimalsFromONG(id);
        actOng.setAnimals(allAnimalsFromONG);
        return OngMapper.INSTANCE.toDTO(ongRepository.save(actOng));
    }

    public void deleteById(Long id) {
        ongRepository.deleteById(id);
    }

    public void deleteAll() {
        ongRepository.deleteAll();
    }

    public OngDTO get(Long id) {
        return OngMapper.INSTANCE.toDTO(findById(id));
    }

}
