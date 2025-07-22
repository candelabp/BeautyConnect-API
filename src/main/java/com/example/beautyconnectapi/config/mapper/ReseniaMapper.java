package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.entity.Resenia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReseniaMapper {
    @Mapping(target = "id", ignore = true)
    Resenia toEntity(ReseniaDTO reseniaDTO);
    ReseniaResponseDTO toResponseDTO(Resenia resenia);
}
