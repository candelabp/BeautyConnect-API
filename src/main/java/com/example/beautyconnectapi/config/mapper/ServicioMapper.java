package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.Servicio;

public interface ServicioMapper {
    Servicio toEntity(ServicioDTO servicioDTO);
    ServicioResponseDTO toResponseDTO(Servicio servicio);
}
