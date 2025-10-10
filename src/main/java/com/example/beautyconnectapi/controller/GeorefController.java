package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.impl.GeoRefService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/georef")
public class GeorefController {

    @Autowired
    private GeoRefService georefService;

    @GetMapping("/provincias")
    public ResponseEntity<?> getProvincias() {
        try {
            Map<String, Object> response = georefService.getProvincias();
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "Error en la API externa: " + e.getStatusText()));
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("error", "Servicio de georef no disponible"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno del servidor"));
        }
    }

    @GetMapping("/localidades")
    public ResponseEntity<?> getLocalidades(
            @RequestParam String provincia,
            @RequestParam(required = false, defaultValue = "100") int max) {
        try {
            if (provincia == null || provincia.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "El parámetro 'provincia' es requerido"));
            }

            Map<String, Object> response = georefService.getLocalidades(provincia, max);
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "Error al obtener localidades para: " + provincia));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno del servidor"));
        }
    }

    @GetMapping("/localidades/search")
    public ResponseEntity<?> searchLocalidades(
            @RequestParam String provincia,
            @RequestParam String q,
            @RequestParam(required = false, defaultValue = "50") int limit) {
        try {
            if (q == null || q.trim().length() < 2) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "La búsqueda debe tener al menos 2 caracteres"));
            }

            Map<String, Object> response = georefService.searchLocalidades(provincia, q.trim(), limit);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error en la búsqueda"));
        }
    }
}