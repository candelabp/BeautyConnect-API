package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import com.example.beautyconnectapi.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> crear(@Valid @RequestBody TurnoDTO dto) {
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

    @PutMapping("/update/{id}") //el update es innecesario
    public ResponseEntity<TurnoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TurnoDTO dto) {
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

    @PutMapping("/{id}/estado/{estado}")
    public ResponseEntity<TurnoResponseDTO> cambiarEstado(@PathVariable Long id, @PathVariable EstadoTurno estado) {
        return ResponseEntity.ok(turnoService.cambiarEstado(id, estado));
    }

    @GetMapping("/centro/{id}")
    public ResponseEntity<List<TurnoResponseDTO>> porCentro(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.obtenerPorCentro(id));
    }

    @GetMapping("/centro/{id}/fecha/{fecha}")
    public ResponseEntity<Long> contarPorFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha, @PathVariable Long id){
        Long cantidad = turnoService.contarTurnosPorDia(fecha, id);
        return ResponseEntity.ok(cantidad);
    }

    @GetMapping("/centro/{id}/semana/{fecha}")
    public ResponseEntity<Long>  contarPorRango(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha, @PathVariable Long id){
        Long cantidad = turnoService.contarTurnosPorSemana(fecha, id);
        return ResponseEntity.ok(cantidad);
    }
}
