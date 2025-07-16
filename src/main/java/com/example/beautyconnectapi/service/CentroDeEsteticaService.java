package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CentroDeEsteticaService {
    CentroDeEsteticaResponseDTO registrar(CentroDeEsteticaDTO dto);
    List<CentroDeEsteticaResponseDTO> listar();
    List<CentroDeEsteticaResponseDTO> listarPorEstado(Estado estado);
    CentroDeEsteticaResponseDTO cambiarEstado(Long id, Estado nuevoEstado);
}
