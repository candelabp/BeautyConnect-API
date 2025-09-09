package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/save")
    public UsuarioResponseDTO saveUsuario(@RequestBody UsuarioDTO usuarioDto){
        return usuarioService.saveUsuario(usuarioDto);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioResponseDTO getUsuarioById(@PathVariable Long usuarioId){
        return usuarioService.getUsuarioById(usuarioId);
    }

    @PatchMapping("/delete/{usuarioId}")
    public UsuarioResponseDTO deleteUsuario(@PathVariable Long usuarioId){
        return usuarioService.deleteUsuario(usuarioId);
    }

    @PatchMapping("/obtenerPorEmail/{mail}")
    public UsuarioResponseDTO findByMail(@PathVariable String mail){
        return usuarioService.findByMail(mail);
    }

    @PatchMapping("/existePorUid/{uid}")
    public boolean existsByUid(String uid) {
        return usuarioService.existsByUid(uid);
    }
}
