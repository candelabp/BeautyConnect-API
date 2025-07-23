package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
public class ServicioController {
    private final ServicioService servicioService;

    private ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping("/save")
    public ServicioResponseDTO saveServicio(ServicioDTO servicioDTO){
        return servicioService.saveServicio(servicioDTO);
    }

    @GetMapping("/{id}")
    public ServicioResponseDTO getServicioById(Long servicioId){
        return servicioService.getServicioById(servicioId);
    }

    @PatchMapping("/update")
    public ServicioResponseDTO updateServicio(Long servicioId, ServicioDTO servicioDTO){
        return servicioService.updateServicio(servicioId, servicioDTO);
    }

    @PatchMapping("/delete")
    public ServicioResponseDTO deleteServicio(Long servicioId){
        return servicioService.deleteServicio(servicioId);
    }

    @GetMapping("/obtenerPorCentro")
    public List<ServicioResponseDTO> obtenerServiciosPorCentroId(Long centroDeEsteticaId){
        return  servicioService.obtenerServiciosPorCentroId(centroDeEsteticaId);
    }

}
