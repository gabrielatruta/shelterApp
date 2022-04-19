package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.SizeMapper;
import com.example.shelterapp.animal.model.Size;
import com.example.shelterapp.animal.model.dto.SizeDTO;
import com.example.shelterapp.animal.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    public List<SizeDTO> allSizes() {
        return sizeRepository.findAll().stream()
                .map(sizeMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Size findById(Long id) {
        return sizeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no size with id = " + id));
    }

    public SizeDTO createSize(SizeDTO sizeDTO) {
        Size newSize = sizeMapper.fromDTO(sizeDTO);
        List<String> allSizes = sizeRepository.findAll().stream()
                .map(x -> x.getSize().getName())
                .collect(Collectors.toList());
        if (!allSizes.contains(sizeDTO.getSize()))
            return sizeMapper.toDTO(sizeRepository.save(newSize));
        else
            try {
                throw new InstanceAlreadyExistsException(sizeDTO.getSize() + " size already exists!");
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            }
        return null;
    }

    public void deleteSizeById(Long id) {
        sizeRepository.deleteById(id);
    }

    public void deleteAllSizes() {
        sizeRepository.deleteAll();
    }

    public SizeDTO getSize(Long id) {
        return sizeMapper.toDTO(findById(id));
    }
}
