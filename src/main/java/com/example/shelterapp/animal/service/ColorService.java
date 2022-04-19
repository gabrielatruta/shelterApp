package com.example.shelterapp.animal.service;

import com.example.shelterapp.animal.mapper.ColorMapper;
import com.example.shelterapp.animal.model.Color;
import com.example.shelterapp.animal.model.dto.ColorDTO;
import com.example.shelterapp.animal.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    public List<ColorDTO> allColors() {
        return colorRepository.findAll().stream()
                .map(colorMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Color findById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no color with id = " + id));
    }

    public ColorDTO createColor(ColorDTO colorDTO) {
        Color newColor = colorMapper.fromDTO(colorDTO);
        List<String> allColors = colorRepository.findAll().stream()
                .map(x -> x.getColor().getName())
                .collect(Collectors.toList());
        if (!allColors.contains(colorDTO.getColor()))
            return colorMapper.toDTO(colorRepository.save(newColor));
        else
            try {
                throw new InstanceAlreadyExistsException(colorDTO.getColor() + " color already exists!");
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            }
        return null;
    }

    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }

    public void deleteAllColors() {
        colorRepository.deleteAll();
    }

    public ColorDTO getColor(Long id) {
        return colorMapper.toDTO(findById(id));
    }
}
