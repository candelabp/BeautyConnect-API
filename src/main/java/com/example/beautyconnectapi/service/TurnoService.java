package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.model.enums.EstadoTurno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoService {
    TurnoResponseDTO crear(TurnoDTO dto);
    List<TurnoResponseDTO> listarTodos();
    List<TurnoResponseDTO> listarPorCliente(Long clienteId);
//    List<TurnoResponseDTO> listarPorPrestador(Long prestadorId);
    TurnoResponseDTO cambiarEstado(Long turnoId, EstadoTurno nuevoEstado);
    TurnoResponseDTO obtenerPorId(Long id);
    TurnoResponseDTO actualizar(Long id, TurnoDTO dto);
    List<TurnoResponseDTO> obtenerPorCentro(Long idCentro);
    Long contarTurnosPorDia(LocalDate fecha, Long centroId);
    Long contarTurnosPorSemana(LocalDate fecha, Long centroId);
}