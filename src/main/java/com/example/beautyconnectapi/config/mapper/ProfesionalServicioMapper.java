package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.ProfesionalServicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ServicioMapper.class, ProfesionalMapper.class, DisponibilidadMapper.class})
public interface ProfesionalServicioMapper {
    @Mapping(target = "id", ignore = true)
    ProfesionalServicio toEntity(ProfesionalServicioDTO profesionalServicioDTO);
    ProfesionalServicioResponseDTO toResponseDTO(ProfesionalServicio profesionalServicio);
}
