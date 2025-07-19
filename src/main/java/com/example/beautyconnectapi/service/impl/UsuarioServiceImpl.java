package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.UsuarioMapper;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.model.entity.Usuario;
import com.example.beautyconnectapi.repository.UsuarioRepository;
import com.example.beautyconnectapi.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO saveUsuario(UsuarioDTO usuarioDto){
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO getUsuarioById(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO deleteUsuario(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActive(false);
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO findByEmail(String email){
        Usuario usuario = usuarioRepository.getUsuarioByEmail(email);
        return usuarioMapper.toResponseDTO(usuario);
    }

}
