package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO saveUsuario(UsuarioDTO usuarioDto);
    UsuarioResponseDTO getUsuarioById(Long usuarioId);
    UsuarioResponseDTO deleteUsuario(Long usuarioId);
    UsuarioResponseDTO findByMail(String mail);
}
