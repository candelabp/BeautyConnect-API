package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.service.CentroDeEsteticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centrodeestetica")
public class CentroDeEsteticaController {
    @Autowired
    private CentroDeEsteticaService centroDeEsteticaService;

    public CentroDeEsteticaController(CentroDeEsteticaService centroDeEsteticaService) {
        this.centroDeEsteticaService = centroDeEsteticaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<CentroDeEsteticaResponseDTO> registrar(@RequestBody CentroDeEsteticaDTO centroDeEsteticaDto) {
        return ResponseEntity.ok(centroDeEsteticaService.registrar(centroDeEsteticaDto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CentroDeEsteticaResponseDTO>> listar() {
        return ResponseEntity.ok(centroDeEsteticaService.listar());
    }

    @GetMapping("/listarEstado")
    public ResponseEntity<List<CentroDeEsteticaResponseDTO>> listarPorEstado(Estado estado) {
        return ResponseEntity.ok(centroDeEsteticaService.listarPorEstado(estado));
    }

    @PutMapping("/cambiarEstado")
    public ResponseEntity<CentroDeEsteticaResponseDTO> cambiarEstado(Long id, Estado estado) {
        return ResponseEntity.ok(centroDeEsteticaService.cambiarEstado(id, estado));
    }
}
