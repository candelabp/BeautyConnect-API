package com.example.beautyconnectapi.controller;



import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;

import com.example.beautyconnectapi.service.HorarioCentroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horariocentro")
public class HorarioCentroController {
    private final HorarioCentroService horarioCentroService;
    public HorarioCentroController(HorarioCentroService horarioCentroService) {
        this.horarioCentroService = horarioCentroService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HorarioCentroResponseDTO> updateHorarioCentro(@PathVariable("id") Long horarioId, @Valid @RequestBody HorarioCentroDTO horarioCentroDTO){
        return ResponseEntity.ok(horarioCentroService.updateHorarioCentro(horarioCentroDTO,horarioId));
    }

    @PostMapping
    public ResponseEntity<HorarioCentroResponseDTO> saveHorarioCentro (@Valid @RequestBody HorarioCentroDTO horarioCentroDTO){
        HorarioCentroResponseDTO resp = horarioCentroService.saveHorarioCentro(horarioCentroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

}
