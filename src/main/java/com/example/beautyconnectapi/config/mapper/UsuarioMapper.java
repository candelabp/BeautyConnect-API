package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.model.entity.Usuario;

public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO usuarioDTO);
    UsuarioResponseDTO toResponseDTO(Usuario usuario);
}
