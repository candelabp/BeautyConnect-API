package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.service.ReseniaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenia")
public class ReseniaController {

    private final ReseniaService reseniaService;

    public ReseniaController(ReseniaService reseniaService){
        this.reseniaService = reseniaService;
    }

    // POST /api/resenia
    @PostMapping
    public ResponseEntity<ReseniaResponseDTO> crear(@Valid @RequestBody ReseniaDTO dto){
        return ResponseEntity.ok(reseniaService.crear(dto));
    }

    // GET /api/resenia
    @GetMapping
    public ResponseEntity<List<ReseniaResponseDTO>> listar() {
        return ResponseEntity.ok(reseniaService.listarTodas());
    }

    // GET /api/resenia/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ReseniaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reseniaService.obtenerPorId(id));
    }

    // PUT /api/resenia/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<ReseniaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ReseniaDTO dto) {
        return ResponseEntity.ok(reseniaService.actualizar(id, dto));
    }

    // GET /api/resenia/centro/{centroId}
    @GetMapping("/centro/{centroId}")
    public ResponseEntity<List<ReseniaResponseDTO>> listarPorCentro(@PathVariable Long centroId){
        return ResponseEntity.ok(reseniaService.listarPorCentro(centroId));
    }
}
