package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;


import java.util.List;


public interface PrestadorDeServicioService {
    PrestadorDeServicioResponseDTO registrar(PrestadorDeServicioDTO dto);
    List<PrestadorDeServicioResponseDTO> listarActivos();
    PrestadorDeServicioResponseDTO buscarPorId(Long id);
    PrestadorDeServicioResponseDTO actualizar(Long id, PrestadorDeServicioDTO dto);
    PrestadorDeServicioResponseDTO findByUsuarioId(Long id);
    PrestadorDeServicioResponseDTO buscarPorUid(String uid);
}
