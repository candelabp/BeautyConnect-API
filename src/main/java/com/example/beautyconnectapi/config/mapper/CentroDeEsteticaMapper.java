package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTOSimple;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTOSimple;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, ServicioMapper.class, HorarioCentroMapper.class})
public interface CentroDeEsteticaMapper {
    @Mapping(target = "id", ignore = true)
    CentroDeEstetica toEntity(CentroDeEsteticaDTO centroDeEsteticaDTO);
    CentroDeEstetica toEntityD(CentroDeEsteticaDTOSimple centroDeEsteticaDTOSimple);
    CentroDeEsteticaResponseDTO toResponseDTO(CentroDeEstetica centroDeEstetica);
    CentroDeEsteticaResponseDTOSimple toResponseDTOSimple(CentroDeEstetica centroDeEstetica);
}
