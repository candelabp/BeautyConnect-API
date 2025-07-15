package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;

public interface PrestadorDeServicioMapper {
    PrestadorDeServicio toEntity(PrestadorDeServicioDTO prestadorDeServicioDTO);
    PrestadorDeServicioResponseDTO toResponseDTO(PrestadorDeServicio prestadorDeServicio);
}
