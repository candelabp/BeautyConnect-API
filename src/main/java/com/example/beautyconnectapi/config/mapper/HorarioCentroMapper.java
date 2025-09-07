package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;
import com.example.beautyconnectapi.model.entity.HorarioCentro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HorarioCentroMapper {
    @Mapping(target = "id", ignore = true)
    HorarioCentro toEntity(HorarioCentroDTO horarioCentroDTO);
    HorarioCentroResponseDTO toResponseDTO(HorarioCentro horarioCentro);
}
