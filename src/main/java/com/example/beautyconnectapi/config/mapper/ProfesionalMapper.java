package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.entity.Profesional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfesionalMapper {
    @Mapping(target = "id", ignore = true)
    Profesional toEntity(ProfesionalDTO profesionalDTO);
    ProfesionalResponseDTO toResponseDTO(Profesional profesional);
}
