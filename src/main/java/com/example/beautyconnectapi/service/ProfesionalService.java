package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;

import java.util.List;

public interface ProfesionalService {
    ProfesionalResponseDTO saveProfesional(ProfesionalDTO profesionalDto);
    ProfesionalResponseDTO getProfesionalById(Long profesionalId);
    ProfesionalResponseDTO updateProfesional(Long profesionalId, ProfesionalDTO profesionalDto);
    ProfesionalResponseDTO deleteProfesional(Long profesionalId);
    List<ProfesionalResponseDTO> getAllProfesionales();
}
