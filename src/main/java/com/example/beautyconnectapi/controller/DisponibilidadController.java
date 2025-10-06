package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.service.DisponibilidadService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    public DisponibilidadController(DisponibilidadService disponibilidadService) {
        this.disponibilidadService = disponibilidadService;
    }

    @PostMapping("/save")
    public DisponibilidadResponseDTO saveDisponibilidad(@Valid @RequestBody DisponibilidadDTO disponibilidadDto){
        return disponibilidadService.saveDisponibilidad(disponibilidadDto);
    }

    @GetMapping("/{id}")
    public DisponibilidadResponseDTO getDisponibilidadById(@PathVariable Long id){
        return disponibilidadService.getDisponibilidadById(id);
    }

    @PostMapping("/update/{id}")
    public DisponibilidadResponseDTO updateDisponibilidad(@PathVariable Long id, @Valid @RequestBody DisponibilidadDTO disponibilidadDto){
        return disponibilidadService.updateDisponibilidad(id, disponibilidadDto);
    }

    @PostMapping("/delete/{id}")
    public DisponibilidadResponseDTO deleteDisponibilidad(@PathVariable Long id){
        return disponibilidadService.deleteDisponibilidad(id);
    }



}
