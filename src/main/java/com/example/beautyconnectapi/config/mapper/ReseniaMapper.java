package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.entity.Resenia;

public interface ReseniaMapper {
    Resenia toEntity(ReseniaDTO reseniaDTO);
    ReseniaResponseDTO toResponseDTO(Resenia resenia);
}
