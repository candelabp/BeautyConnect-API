package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTOSimple;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTOSimple;
import com.example.beautyconnectapi.model.entity.Resenia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class})
public interface ReseniaMapper {
    @Mapping(target = "id", ignore = true)
    Resenia toEntity(ReseniaDTO reseniaDTO);
    Resenia toEntity(ReseniaDTOSimple reseniaDTO);
    ReseniaResponseDTO toResponseDTO(Resenia resenia);
    ReseniaResponseDTOSimple toResponseDTOSimple(Resenia resenia);
}
