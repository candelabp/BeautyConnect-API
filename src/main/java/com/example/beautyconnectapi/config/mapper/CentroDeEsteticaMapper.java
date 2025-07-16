package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CentroDeEsteticaMapper {
    CentroDeEstetica toEntity(CentroDeEsteticaDTO centroDeEsteticaDTO);
    CentroDeEsteticaResponseDTO toResponseDTO(CentroDeEstetica centroDeEstetica);
}
