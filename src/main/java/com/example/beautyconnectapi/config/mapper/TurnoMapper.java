package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.entity.Turno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TurnoMapper {
    Turno toEntity(TurnoDTO turnoDTO);
    TurnoResponseDTO toResponseDTO(Turno turno);
    TurnoDTO toDto(Turno turno);
}
