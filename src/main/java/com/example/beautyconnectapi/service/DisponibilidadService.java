package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;


public interface DisponibilidadService {
    DisponibilidadResponseDTO saveDisponibilidad(DisponibilidadDTO disponibilidadDto);
    DisponibilidadResponseDTO getDisponibilidadById(Long disponibilidadId);
    DisponibilidadResponseDTO updateDisponibilidad(Long disponibilidadId, DisponibilidadDTO disponibilidadDto);
    DisponibilidadResponseDTO deleteDisponibilidad(Long disponibilidadId);
}
