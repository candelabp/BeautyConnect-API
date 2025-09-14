package com.example.beautyconnectapi.controller;


import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralCreateDTO;
import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralResponseDTO;
import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralUpdateDTO;
import com.example.beautyconnectapi.service.JornadaLaboralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jornadas")
public class JornadaLaboralController {

    private final JornadaLaboralService service;

    @PostMapping
    public ResponseEntity<JornadaLaboralResponseDTO> create(@RequestBody JornadaLaboralCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<JornadaLaboralResponseDTO>> bulk(@RequestBody List<JornadaLaboralCreateDTO> dtos) {
        return ResponseEntity.ok(service.bulkCreate(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JornadaLaboralResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/profesional/{profesionalId}")
    public ResponseEntity<List<JornadaLaboralResponseDTO>> listByProfesional(@PathVariable Long profesionalId) {
        return ResponseEntity.ok(service.listByProfesional(profesionalId));
    }

    @GetMapping("/profesional/{profesionalId}/dia/{dia}")
    public ResponseEntity<List<JornadaLaboralResponseDTO>> listByProfesionalAndDia(
            @PathVariable Long profesionalId, @PathVariable String dia) {
        return ResponseEntity.ok(service.listByProfesionalAndDia(profesionalId, dia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JornadaLaboralResponseDTO> update(
            @PathVariable Long id,
             @RequestBody JornadaLaboralUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<JornadaLaboralResponseDTO> toggle(@PathVariable Long id, @RequestParam boolean activo) {
        return ResponseEntity.ok(service.toggleActivo(id, activo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
