package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.ProfesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.ProfesionalServicio;
import com.example.beautyconnectapi.service.ProfesionalServicioService;
import com.example.beautyconnectapi.service.ServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;
    private final ProfesionalServicioService profesionalServicioService;

    @GetMapping
    public List<ServicioResponseDTO> getAll() {
        return servicioService.getAllServicios();
    }

 //   @PostMapping
   // public ServicioResponseDTO create(@RequestBody ServicioDTO servicioDTO) {
     //   return servicioService.saveServicio(servicioDTO);
    //}

    @GetMapping("/{id}")
    public ServicioResponseDTO getById(@PathVariable("id") Long servicioId) {
        return servicioService.getServicioById(servicioId);
    }

    @PatchMapping("/update/{id}")
    public ServicioResponseDTO update(@PathVariable("id") Long servicioId,
                                      @RequestBody ServicioDTO servicioDTO) {
        return servicioService.updateServicio(servicioId, servicioDTO);
    }

    @DeleteMapping("/{id}")
    public ServicioResponseDTO delete(@PathVariable("id") Long servicioId) {
        return servicioService.deleteServicio(servicioId);
    }

    @GetMapping("/by-centro/{centroId}")
    public List<ServicioResponseDTO> obtenerPorCentro(@PathVariable("centroId") Long centroDeEsteticaId) {
        return servicioService.obtenerServiciosPorCentroId(centroDeEsteticaId);
    }

    @GetMapping("/por-uid/{uid}")
    public ResponseEntity<List<ServicioResponseDTO>> listarPorUid(@PathVariable String uid) {
        List<ServicioResponseDTO> data = servicioService.listarPorUid(uid);
        return ResponseEntity.ok(data);
    }
    @PostMapping
    public ResponseEntity<ServicioResponseDTO> crear(@Valid @RequestBody ServicioDTO dto) {
        return ResponseEntity.ok(servicioService.crear(dto));
    }


}
