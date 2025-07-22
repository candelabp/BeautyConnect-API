package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.entity.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurnoMapper {
    @Mapping(target = "id", ignore = true)
    Turno toEntity(TurnoDTO turnoDTO);
    TurnoResponseDTO toResponseDTO(Turno turno);
}
