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

    private final CentroDeEsteticaService centroDeEsteticaService;

    public CentroDeEsteticaController(CentroDeEsteticaService centroDeEsteticaService) {
        this.centroDeEsteticaService = centroDeEsteticaService;
    }

    // POST /api/centrodeestetica
    @PostMapping
    public ResponseEntity<CentroDeEsteticaResponseDTO> registrar(@RequestBody CentroDeEsteticaDTO dto) {
        return ResponseEntity.ok(centroDeEsteticaService.registrar(dto));
    }

    // GET /api/centrodeestetica
    @GetMapping
    public ResponseEntity<List<CentroDeEsteticaResponseDTO>> listar() {
        return ResponseEntity.ok(centroDeEsteticaService.listar());
    }

    // GET /api/centrodeestetica/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CentroDeEsteticaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(centroDeEsteticaService.obtenerPorId(id));
    }

    // PUT /api/centrodeestetica/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<CentroDeEsteticaResponseDTO> actualizar(@PathVariable Long id, @RequestBody CentroDeEsteticaDTO dto) {
        return ResponseEntity.ok(centroDeEsteticaService.actualizar(id, dto));
    }

    // DELETE /api/centrodeestetica/{id}


    // GET /api/centrodeestetica/listarEstado?estado=ACTIVO
    @GetMapping("/listarEstado")
    public ResponseEntity<List<CentroDeEsteticaResponseDTO>> listarPorEstado(@RequestParam Estado estado) {
        return ResponseEntity.ok(centroDeEsteticaService.listarPorEstado(estado));
    }

    // PUT /api/centrodeestetica/cambiarEstado?id=1&estado=ACTIVO
    @PutMapping("/cambiarEstado")
    public ResponseEntity<CentroDeEsteticaResponseDTO> cambiarEstado(@RequestParam Long id, @RequestParam Estado estado) {
        return ResponseEntity.ok(centroDeEsteticaService.cambiarEstado(id, estado));
    }
    @GetMapping("/by-prestador-uid/{uid}")
    public ResponseEntity<CentroDeEsteticaResponseDTO> getByPrestadorUid(@PathVariable String uid) {
        return ResponseEntity.ok(centroDeEsteticaService.obtenerPorPrestadorUid(uid));
    }
}


