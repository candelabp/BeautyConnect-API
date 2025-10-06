package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.service.ProfesionalServicioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prof-servicios")
public class ProfesionalServicioController {

    private final ProfesionalServicioService profesionalServicioService;

    public ProfesionalServicioController(ProfesionalServicioService profesionalServicioService) {
        this.profesionalServicioService = profesionalServicioService;
    }

    @PostMapping
    public ProfesionalServicioResponseDTO createProfesionalServicio(@Valid @RequestBody ProfesionalServicioDTO profesionalServicioDTO) {
        return profesionalServicioService.saveProfServicio(profesionalServicioDTO);
    }

    @GetMapping("/getProfServicio/{id}")
    public List<ProfesionalServicioResponseDTO> getProfServicio(@PathVariable Long id) {
        return profesionalServicioService.getAllByServicioId(id);
    }

   /* @GetMapping("/disponibles/prof/{profId}/servicio/{servicioId}")
    public ProfesionalServicioResponseDTO getByProfServicio(@PathVariable Long profId, @PathVariable Long servicioId){
        return profesionalServicioService.getFechasDisponibles(profId, servicioId);
    }*/

    @PatchMapping ("/cambiarEstado/{id}")
    public ProfesionalServicioResponseDTO cambiarEstado(@PathVariable Long id){
        return profesionalServicioService.cambiarEstado(id);
    }

    @GetMapping("/getProfServicio")
    public List<ProfesionalServicioResponseDTO> getByProfesional(Long profId){
        return profesionalServicioService.getByProfesional(profId);
    }

    @GetMapping("/{profId}/servicios/{servicioId}")
    public  ProfesionalServicioResponseDTO getByProfesionalAndServicio(
            @PathVariable Long profId,
            @PathVariable Long servicioId) {
        return profesionalServicioService.getByProfesionalAndServicio(profId, servicioId);
    }




}
