package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    private UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/save")
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
