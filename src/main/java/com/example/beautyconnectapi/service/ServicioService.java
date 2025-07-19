package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;

import java.util.List;

public interface ServicioService {
    ServicioResponseDTO saveServicio(ServicioDTO servicioDTO);
    ServicioResponseDTO getServicioById(Long servicioId);
    ServicioResponseDTO updateServicio(Long servicioId, ServicioDTO servicioDTO);
    ServicioResponseDTO deleteServicio(Long servicioId);
    List<ServicioResponseDTO> obtenerServiciosPorCentroId(Long centroDeEsteticaId);
}
