package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.ProfesionalServicio;

import java.util.List;

public interface ProfesionalServicioService {
    ProfesionalServicioResponseDTO saveProfServicio(ProfesionalServicioDTO profesionalServicioDto);

    ProfesionalServicioResponseDTO getProfServicoById(Long id);

    ProfesionalServicioResponseDTO updateProfServicio(Long id, ProfesionalServicioDTO profesionalServicioDto);

    ProfesionalServicioResponseDTO cambiarEstado(Long id);

  //  ProfesionalServicioResponseDTO getFechasDisponibles(Long profId, Long servicioId);

    List<ProfesionalServicioResponseDTO> getAllByServicioId(Long servicioId);
    List<ProfesionalServicioResponseDTO> getByProfesional(Long profId);
    ProfesionalServicioResponseDTO getByProfesionalAndServicio(Long profesionalId, Long servicioId);

   /* ProfesionalServicioResponseDTO update(Long profId);

    ProfesionalServicioResponseDTO asignarServicio(Long profId , Long servicioId);
    ProfesionalServicioResponseDTO desasignarServicio(Long profId , Long servicioId);*/

}