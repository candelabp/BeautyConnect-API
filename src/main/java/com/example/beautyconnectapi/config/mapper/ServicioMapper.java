package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.Servicio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    Servicio toEntity(ServicioDTO servicioDTO);
    ServicioResponseDTO toResponseDTO(Servicio servicio);
}
