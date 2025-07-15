package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.entity.Domicilio;

public interface DomicilioMapper {
    Domicilio toEntity(DomicilioDTO domicilioDTO);
    DomicilioResponseDTO toResponseDTO(Domicilio domicilio);
}
