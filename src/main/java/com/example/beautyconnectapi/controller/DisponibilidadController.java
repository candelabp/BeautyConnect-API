package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.service.DisponibilidadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    public DisponibilidadController(DisponibilidadService disponibilidadService) {
        this.disponibilidadService = disponibilidadService;
    }

    @PostMapping("/save")
    public DisponibilidadResponseDTO saveDisponibilidad(DisponibilidadDTO disponibilidadDto){
        return disponibilidadService.saveDisponibilidad(disponibilidadDto);
    }

    @GetMapping("/{id}")
    public DisponibilidadResponseDTO getDisponibilidadById(Long disponibilidadId){
        return disponibilidadService.getDisponibilidadById(disponibilidadId);
    }

    @PostMapping("/update")
    public DisponibilidadResponseDTO updateDisponibilidad(Long disponibilidadId, DisponibilidadDTO disponibilidadDto){
        return disponibilidadService.updateDisponibilidad(disponibilidadId, disponibilidadDto);
    }

    @PostMapping("/delete")
    public DisponibilidadResponseDTO deleteDisponibilidad(Long disponibilidadId){
        return disponibilidadService.deleteDisponibilidad(disponibilidadId);
    }
}
