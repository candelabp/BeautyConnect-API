package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TurnoService {
    TurnoResponseDTO crear(TurnoDTO dto);
    List<TurnoResponseDTO> listarTodos();
    List<TurnoResponseDTO> listarPorCliente(Long clienteId);
    List<TurnoResponseDTO> listarPorPrestador(Long prestadorId);
    TurnoResponseDTO cambiarEstado(Long turnoId, Estado nuevoEstado);
}