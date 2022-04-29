package com.example.shelterapp.ong;

import com.example.shelterapp.ong.model.dto.OngDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shelterapp.utils.URLMapping.ENTITY;
import static com.example.shelterapp.utils.URLMapping.ONG;

@RestController
@RequestMapping(ONG)
@RequiredArgsConstructor
public class OngController {

    private final OngService ongService;

    @GetMapping
    public List<OngDTO> allOng() {
        return ongService.findAll();
    }

    @PostMapping
    public OngDTO createOng(@RequestBody OngDTO ongDTO) {
        return  ongService.createOng(ongDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteOngById(@PathVariable Long id) {
        ongService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllOng() {
        ongService.deleteAll();
    }

    @PatchMapping(ENTITY)
    public OngDTO editOng(@PathVariable Long id, @RequestBody OngDTO ongDTO) {
        return ongService.editOng(id, ongDTO);
    }

    @GetMapping(ENTITY)
    public OngDTO getOng(@PathVariable Long id) {
        return ongService.get(id);
    }
}
