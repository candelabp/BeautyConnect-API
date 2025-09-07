package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.service.DomicilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/domicilio")
public class DomicilioController {

    private final DomicilioService domicilioService;

    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<DomicilioResponseDTO> saveDomicilio(@RequestBody DomicilioDTO domicilioDto){
        DomicilioResponseDTO resp = domicilioService.saveDomicilio(domicilioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // READ
    @GetMapping("/{domicilioId}")
    public ResponseEntity<DomicilioResponseDTO> getDomicilioById(@PathVariable Long domicilioId){
        DomicilioResponseDTO resp = domicilioService.getDomicilioById(domicilioId);
        return ResponseEntity.ok(resp);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<DomicilioResponseDTO> updateDomicilio(@PathVariable("id") Long domicilioId, @RequestBody DomicilioDTO domicilioDto){
        DomicilioResponseDTO resp = domicilioService.updateDomicilio(domicilioId, domicilioDto);
        return ResponseEntity.ok(resp);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<DomicilioResponseDTO> deleteDomicilio(@PathVariable("id") Long domicilioId){
        DomicilioResponseDTO resp = domicilioService.deleteDomicilio(domicilioId);
        return ResponseEntity.ok(resp);
    }
}
