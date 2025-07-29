package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.service.ProfesionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profesional")
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    private ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @PostMapping("/save")
    public ProfesionalResponseDTO saveProfesional(ProfesionalDTO profesionalDto){
        return profesionalService.saveProfesional(profesionalDto);
    }

    @GetMapping("/{id}")
    public ProfesionalResponseDTO getProfesionalById(Long profesionalId){
        return profesionalService.getProfesionalById(profesionalId);
    }

    @PostMapping("/update")
    public ProfesionalResponseDTO updateProfesional(Long profesionalId, ProfesionalDTO profesionalDto){
        return profesionalService.updateProfesional(profesionalId, profesionalDto);
    }

    @PostMapping("/delete")
    public ProfesionalResponseDTO deleteProfesional(Long profesionalId){
        return profesionalService.deleteProfesional(profesionalId);
    }
}
