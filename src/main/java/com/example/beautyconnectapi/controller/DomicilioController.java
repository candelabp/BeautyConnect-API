package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/domicilio")
public class DomicilioController {
    private final DomicilioService domicilioService;

    private DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @PostMapping("/save")
    public DomicilioResponseDTO saveDomicilio(DomicilioDTO domicilioDto){
        return domicilioService.saveDomicilio(domicilioDto);
    }

    @GetMapping("/{id}")
    public DomicilioResponseDTO getDomicilioById(Long domicilioId){
        return domicilioService.getDomicilioById(domicilioId);
    }

    @PostMapping("/update")
    public DomicilioResponseDTO updateDomicilio(Long domicilioId, DomicilioDTO domicilioDto){
        return domicilioService.updateDomicilio(domicilioId, domicilioDto);
    }

    @PostMapping("/delete")
    public DomicilioResponseDTO deleteDomicilio(Long domicilioId){
        return domicilioService.deleteDomicilio(domicilioId);
    }

    @GetMapping("/obtenerPorCentro")
    public List<DomicilioResponseDTO> getDomiciliosByCentroDeEstetica(Long centroDeEsteticaId){
        return domicilioService.getDomiciliosByCentroDeEstetica(centroDeEsteticaId);
    }
}
