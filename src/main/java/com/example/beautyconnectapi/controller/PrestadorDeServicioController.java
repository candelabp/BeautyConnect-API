package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PrestadorDeServicioResponseDTO>> listar() {
        return ResponseEntity.ok(prestadorDeServicioService.getPrestadoresDeServicios());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PrestadorDeServicioResponseDTO>> listarActivos() {
        return ResponseEntity.ok(prestadorDeServicioService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorDeServicioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prestadorDeServicioService.buscarPorId(id));
    }
    // PUT /api/prestadordeservicio/update/{id}

    @PatchMapping("/update/{id}")
    public ResponseEntity<PrestadorDeServicioResponseDTO> actualizar(@PathVariable Long id, @RequestBody PrestadorDeServicioDTO dto) {
        return ResponseEntity.ok(prestadorDeServicioService.actualizar(id, dto));
    }


    @GetMapping("/uid/{uid}")
    public ResponseEntity<PrestadorDeServicioResponseDTO> obtenerPorUid(@PathVariable String uid) {
        PrestadorDeServicioResponseDTO response = prestadorDeServicioService.obtenerPorUid(uid);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/cambiarEstadoActivo/{id}")
    public ResponseEntity<PrestadorDeServicioResponseDTO> cambiarEstadoActivo(@PathVariable Long id) {
        return ResponseEntity.ok(prestadorDeServicioService.cambiarEstadoActive(id));
    }
}
