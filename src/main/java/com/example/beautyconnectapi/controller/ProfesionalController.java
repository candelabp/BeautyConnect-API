package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.service.ProfesionalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesional")
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

  //  @PostMapping("/save")
    //public ProfesionalResponseDTO saveProfesional(@RequestBody ProfesionalDTO profesionalDto){
      //  return profesionalService.saveProfesional(profesionalDto);
    //}

    @GetMapping("/{id}")
    public ProfesionalResponseDTO getProfesionalById(@PathVariable("id") Long profesionalId){
        return profesionalService.getProfesionalById(profesionalId);
    }

    @PostMapping("/update/{profesionalId}")
    public ProfesionalResponseDTO updateProfesional(@PathVariable Long profesionalId, @Valid @RequestBody ProfesionalDTO profesionalDto){
        return profesionalService.updateProfesional(profesionalId, profesionalDto);
    }

    @DeleteMapping("/delete/{profesionalId}")
    public ProfesionalResponseDTO deleteProfesional(@PathVariable Long profesionalId){
        return profesionalService.deleteProfesional(profesionalId);
    }

    @GetMapping
    public List<ProfesionalResponseDTO> getAllProfesionales() {
        return profesionalService.getAllProfesionales();
    }

    @GetMapping("/por-uid/{uid}")
    public ResponseEntity<List<ProfesionalResponseDTO>> listarPorUid(@PathVariable String uid) {
        return ResponseEntity.ok(profesionalService.listarPorUid(uid));
    }

    @PostMapping
    public ResponseEntity<ProfesionalResponseDTO> crear(@Valid @RequestBody ProfesionalDTO dto) {
        return ResponseEntity.ok(profesionalService.crear(dto));
    }


}