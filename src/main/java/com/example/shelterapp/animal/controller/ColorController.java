package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.service.ColorService;
import com.example.shelterapp.animal.model.dto.ColorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shelterapp.URLMapping.COLOR;
import static com.example.shelterapp.URLMapping.ENTITY;

@RestController
@RequestMapping(COLOR)
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping
    public List<ColorDTO> allColors() {
        return colorService.allColors();
    }

    @PostMapping
    public ColorDTO createColor(@RequestBody ColorDTO colorDTO) {
        return colorService.createColor(colorDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteColorById(@PathVariable Long id) {
        colorService.deleteColor(id);
    }

    @DeleteMapping
    public void deleteColors() {
        colorService.deleteAllColors();
    }

    @GetMapping(ENTITY)
    public ColorDTO getColor(@PathVariable Long id) {
        return colorService.getColor(id);
    }
}
