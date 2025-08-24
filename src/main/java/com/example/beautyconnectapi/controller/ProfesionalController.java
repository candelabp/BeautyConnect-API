package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.service.ProfesionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesional")
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @PostMapping("/save")
    public ProfesionalResponseDTO saveProfesional(@RequestBody ProfesionalDTO profesionalDto){
        return profesionalService.saveProfesional(profesionalDto);
    }

    @GetMapping("/{id}")
    public ProfesionalResponseDTO getProfesionalById(@PathVariable("id") Long profesionalId){
        return profesionalService.getProfesionalById(profesionalId);
    }

    @PostMapping("/update")
    public ProfesionalResponseDTO updateProfesional(@RequestParam Long profesionalId, @RequestBody ProfesionalDTO profesionalDto){
        return profesionalService.updateProfesional(profesionalId, profesionalDto);
    }

    @PostMapping("/delete")
    public ProfesionalResponseDTO deleteProfesional(@RequestParam Long profesionalId){
        return profesionalService.deleteProfesional(profesionalId);
    }

    @GetMapping
    public List<ProfesionalResponseDTO> getAllProfesionales() {
        return profesionalService.getAllProfesionales();
    }
}