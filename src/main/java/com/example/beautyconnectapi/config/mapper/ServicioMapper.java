package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTOSimple;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTOSimple;
import com.example.beautyconnectapi.model.entity.Servicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "centroDeEstetica", ignore = true)
    Servicio toEntity(ServicioDTO servicioDTO);
    Servicio toEntity(ServicioDTOSimple servicioDTOSimple);
    ServicioResponseDTO toResponseDTO(Servicio servicio);
    ServicioResponseDTOSimple toResponseDTOSimple(Servicio servicio);
}
