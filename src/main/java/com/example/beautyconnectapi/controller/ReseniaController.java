package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.service.ReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenia")
public class ReseniaController {
   private final ReseniaService reseniaService;
   public ReseniaController(ReseniaService reseniaService){
       this.reseniaService = reseniaService;
   }

   @PostMapping
    public ResponseEntity<ReseniaResponseDTO> crear(@RequestBody ReseniaDTO dto){
       return ResponseEntity.ok(reseniaService.crear(dto));
   }
   @GetMapping("/centro/{centroId}")
    public ResponseEntity<List<ReseniaResponseDTO>> listarPorCentro(@PathVariable Long centroId){
       return ResponseEntity.ok(reseniaService.listarPorCentro(centroId));
   }
}
