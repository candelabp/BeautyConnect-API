package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/prestadordeservicio")
public class PrestadorDeServicioController {
    private final PrestadorDeServicioService prestadorDeServicioService;

    public PrestadorDeServicioController(PrestadorDeServicioService prestadorDeServicioService) {
        this.prestadorDeServicioService = prestadorDeServicioService;
    }

    @PostMapping
    public ResponseEntity<PrestadorDeServicioResponseDTO> registrar(@RequestBody PrestadorDeServicioDTO dto) {
        return ResponseEntity.ok(prestadorDeServicioService.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PrestadorDeServicioResponseDTO>> listarActivos() {
        return ResponseEntity.ok(prestadorDeServicioService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorDeServicioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prestadorDeServicioService.buscarPorId(id));
    }
}
