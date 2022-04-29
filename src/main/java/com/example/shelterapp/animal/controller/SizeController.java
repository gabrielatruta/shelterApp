package com.example.shelterapp.animal.controller;

import com.example.shelterapp.animal.service.SizeService;
import com.example.shelterapp.animal.model.dto.SizeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shelterapp.utils.URLMapping.ENTITY;
import static com.example.shelterapp.utils.URLMapping.SIZE;

@RestController
@RequestMapping(SIZE)
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @GetMapping
    public List<SizeDTO> allSizes() {
        return sizeService.allSizes();
    }

    @PostMapping
    public SizeDTO createSize(@RequestBody SizeDTO sizeDTO) {
        return sizeService.createSize(sizeDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteSizeById(@PathVariable Long id) {
        sizeService.deleteSizeById(id);
    }

    @DeleteMapping
    public void deleteAllSizes() {
        sizeService.deleteAllSizes();
    }

    @GetMapping(ENTITY)
    public SizeDTO getSize(@PathVariable Long id) {
        return sizeService.getSize(id);
    }
}
