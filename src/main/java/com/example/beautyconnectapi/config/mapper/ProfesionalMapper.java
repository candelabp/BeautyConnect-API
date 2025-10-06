package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTOSimple;
import com.example.beautyconnectapi.model.entity.Profesional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProfesionalMapper.class, ServicioMapper.class, DisponibilidadMapper.class, CentroDeEsteticaMapper.class})
public interface ProfesionalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "centroDeEstetica", ignore = true)
    Profesional toEntity(ProfesionalDTO profesionalDTO);
    ProfesionalResponseDTO toResponseDTO(Profesional profesional);
    ProfesionalResponseDTOSimple toResponseDTOSimple(Profesional profesional);
}
