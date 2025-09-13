package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.service.DisponibilidadService;
import com.example.beautyconnectapi.service.ProfesionalServicioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;
    private final ProfesionalServicioService profesionalServicioService;

    public DisponibilidadController(DisponibilidadService disponibilidadService, ProfesionalServicioService profesionalServicioService) {
        this.disponibilidadService = disponibilidadService;
        this.profesionalServicioService = profesionalServicioService;
    }

    @PostMapping("/save")
    public DisponibilidadResponseDTO saveDisponibilidad(@RequestBody DisponibilidadDTO disponibilidadDto){
        return disponibilidadService.saveDisponibilidad(disponibilidadDto);
    }

    @GetMapping("/{id}")
    public DisponibilidadResponseDTO getDisponibilidadById(@PathVariable Long id){
        return disponibilidadService.getDisponibilidadById(id);
    }

    @PostMapping("/update/{id}")
    public DisponibilidadResponseDTO updateDisponibilidad(@PathVariable Long id, @RequestBody DisponibilidadDTO disponibilidadDto){
        return disponibilidadService.updateDisponibilidad(id, disponibilidadDto);
    }

    @PostMapping("/delete/{id}")
    public DisponibilidadResponseDTO deleteDisponibilidad(@PathVariable Long id){
        return disponibilidadService.deleteDisponibilidad(id);
    }



}
