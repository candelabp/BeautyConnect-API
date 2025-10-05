package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import jakarta.mail.MessagingException;

import java.util.List;


public interface CentroDeEsteticaService {
    CentroDeEsteticaResponseDTO registrar(CentroDeEsteticaDTO dto);
    List<CentroDeEsteticaResponseDTO> listar();
    List<CentroDeEsteticaResponseDTO> listarPorEstado(Estado estado);
    List<CentroDeEsteticaResponseDTO> listarPorEstadoyActive(Estado estado, boolean active);
    CentroDeEsteticaResponseDTO cambiarEstado(Long id, Estado nuevoEstado) throws MessagingException;
    CentroDeEsteticaResponseDTO obtenerPorId(Long id);
    CentroDeEsteticaResponseDTO actualizar(Long id, CentroDeEsteticaDTO dto);
    CentroDeEsteticaResponseDTO obtenerPorPrestador(Long idPrestador);
    Long obtenerIdPorUid(String uid);
    CentroDeEsteticaResponseDTO activar_desactivar(Long id);
}
