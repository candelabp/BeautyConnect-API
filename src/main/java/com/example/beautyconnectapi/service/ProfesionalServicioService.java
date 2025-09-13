package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioResponseDTO;

import java.util.List;

public interface ProfesionalServicioService {
    ProfesionalServicioResponseDTO saveProfServico(ProfesionalServicioDTO profesionalServicioDto);

    ProfesionalServicioResponseDTO getProfServicoById(Long id);

    ProfesionalServicioResponseDTO updateProfServico(Long id, ProfesionalServicioDTO profesionalServicioDto);

    ProfesionalServicioResponseDTO deleteProfServico(Long id);

    ProfesionalServicioResponseDTO getFechasDisponibles(Long profId, Long servicioId);

    List<ProfesionalServicioResponseDTO> getAllByServicioId(Long servicioId);

}