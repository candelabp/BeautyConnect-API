package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;

import java.util.List;

public interface DomicilioService {
    DomicilioResponseDTO saveDomicilio(DomicilioDTO domicilioDto);
    DomicilioResponseDTO getDomicilioById(Long domicilioId);
    DomicilioResponseDTO updateDomicilio(Long domicilioId, DomicilioDTO domicilioDto);
    DomicilioResponseDTO deleteDomicilio(Long domicilioId);
    List<DomicilioResponseDTO> getDomiciliosByCentroDeEstetica(Long centroDeEsteticaId);
}
