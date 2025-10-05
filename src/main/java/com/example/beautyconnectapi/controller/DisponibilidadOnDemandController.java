package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.horarioDisponible.HorariosDisponiblesResponse;
import com.example.beautyconnectapi.service.impl.DisponibilidadOnDemandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
@RestController
@RequestMapping("/api/profesionales")
@RequiredArgsConstructor
public class DisponibilidadOnDemandController {

    private final DisponibilidadOnDemandServiceImpl service;

    // GET /api/profesionales/{profId}/servicios/{servicioId}/disponibles?fecha=YYYY-MM-DD&step=10
    @GetMapping("/{profId}/serviciosdispo/{servicioId}/disponibles")
    public HorariosDisponiblesResponse getDisponibles(
            @PathVariable Long profId,
            @PathVariable Long servicioId,
            @RequestParam("fecha") LocalDate fecha,
            @RequestParam(value = "step", required = false, defaultValue = "10") int stepMin
    ) {
        return service.generarIniciosDisponibles(profId, servicioId, fecha, stepMin);
    }

    @GetMapping("/{profId}/servicios/{servicioId}/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(Map.of("ok", true));
    }


}