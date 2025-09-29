package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;

public interface HorarioCentroService {
    HorarioCentroResponseDTO updateHorarioCentro(HorarioCentroDTO dto, Long horarioId);
    HorarioCentroResponseDTO saveHorarioCentro(HorarioCentroDTO dto);
}
