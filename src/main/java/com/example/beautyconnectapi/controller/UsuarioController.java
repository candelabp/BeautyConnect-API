package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/save")
    public UsuarioResponseDTO saveUsuario(UsuarioDTO usuarioDto){
        return usuarioService.saveUsuario(usuarioDto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO getUsuarioById(Long usuarioId){
        return usuarioService.getUsuarioById(usuarioId);
    }

    @PatchMapping("/delete")
    public UsuarioResponseDTO deleteUsuario(Long usuarioId){
        return usuarioService.deleteUsuario(usuarioId);
    }

    @PatchMapping("/obtenerPorEmail")
    public UsuarioResponseDTO findByMail(String mail){
        return usuarioService.findByMail(mail);
    }
}
