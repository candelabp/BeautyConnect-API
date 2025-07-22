package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrestadorDeServicioMapper {
    @Mapping(target = "id", ignore = true)
    PrestadorDeServicio toEntity(PrestadorDeServicioDTO prestadorDeServicioDTO);
    PrestadorDeServicioResponseDTO toResponseDTO(PrestadorDeServicio prestadorDeServicio);
}
