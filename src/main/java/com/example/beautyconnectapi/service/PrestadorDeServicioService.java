package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;


import java.util.List;


public interface PrestadorDeServicioService {
    PrestadorDeServicioResponseDTO registrar(PrestadorDeServicioDTO dto);
    List<PrestadorDeServicioResponseDTO> listarActivos();
    PrestadorDeServicioResponseDTO buscarPorId(Long id);

}
