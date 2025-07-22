package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CentroDeEsteticaMapper {
    @Mapping(target = "id", ignore = true)
    CentroDeEstetica toEntity(CentroDeEsteticaDTO centroDeEsteticaDTO);
    CentroDeEsteticaResponseDTO toResponseDTO(CentroDeEstetica centroDeEstetica);
}
