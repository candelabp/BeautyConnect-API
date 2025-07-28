package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReseniaService {
    ReseniaResponseDTO crear(ReseniaDTO dto);
    List<ReseniaResponseDTO> listarPorCentro( Long centroId);
    List<ReseniaResponseDTO> listarTodas();
    ReseniaResponseDTO obtenerPorId(Long id);
    ReseniaResponseDTO actualizar(Long id, ReseniaDTO dto);
}
