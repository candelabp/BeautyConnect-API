package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import com.example.beautyconnectapi.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> crear(@RequestBody TurnoDTO dto) {
        return ResponseEntity.ok(turnoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.obtenerPorId(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TurnoResponseDTO> actualizar(@PathVariable Long id, @RequestBody TurnoDTO dto) {
        return ResponseEntity.ok(turnoService.actualizar(id, dto));
    }


    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<TurnoResponseDTO>> porCliente(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.listarPorCliente(id));
    }

//    @GetMapping("/prestador/{prestadorId}")
//    public ResponseEntity<List<TurnoResponseDTO>> porPrestador(@PathVariable Long prestadorId) {
//        return ResponseEntity.ok(turnoService.listarPorPrestador(prestadorId));
//    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<TurnoResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoTurno estado
    ) {
        return ResponseEntity.ok(turnoService.cambiarEstado(id, estado));
    }
    @GetMapping("/centro/{id}")
    public ResponseEntity<List<TurnoResponseDTO>> porCentro(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.obtenerPorCentro(id));
    }
}
