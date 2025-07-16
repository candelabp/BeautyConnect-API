package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> crearTurno(@RequestBody TurnoDTO dto) {
        return ResponseEntity.ok(turnoService.crearTurno(dto));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<TurnoDTO>> getTurnosPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(turnoService.listarPorCliente(clienteId));
    }

    @GetMapping("/prestador/{prestadorId}")
    public ResponseEntity<List<TurnoDTO>> getTurnosPorPrestador(@PathVariable Long prestadorId) {
        return ResponseEntity.ok(turnoService.listarPorPrestador(prestadorId));
    }
}
