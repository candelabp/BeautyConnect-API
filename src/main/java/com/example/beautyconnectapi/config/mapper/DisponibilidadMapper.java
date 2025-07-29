package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.entity.Disponibilidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisponibilidadMapper {
    @Mapping(target = "id", ignore = true)
    Disponibilidad toEntity(DisponibilidadDTO disponibilidadDTO);
    DisponibilidadResponseDTO toResponseDTO(Disponibilidad disponibilidad);
}
