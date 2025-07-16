package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TurnoService {
    TurnoDTO crearTurno( TurnoDTO turnoDTO);
    List<TurnoDTO> listarPorCliente(Long clienteId);
    List<TurnoDTO> listarPorPrestador(Long prestadorId);


}
