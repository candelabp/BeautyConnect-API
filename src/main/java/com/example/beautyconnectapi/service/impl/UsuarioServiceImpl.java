package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.firebase.FirebaseConfig;
import com.example.beautyconnectapi.config.mapper.UsuarioMapper;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.model.entity.Usuario;
import com.example.beautyconnectapi.repository.UsuarioRepository;
import com.example.beautyconnectapi.service.UsuarioService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final FirebaseConfig firebaseConfig;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, FirebaseConfig firebaseConfig) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.firebaseConfig = firebaseConfig;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO saveUsuario(UsuarioDTO usuarioDto){
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuarioRepository.save(usuario);
        try {
            firebaseConfig.asignarRol(usuario.getUid(), usuarioDto.getRol().toString());
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("No se pudo asignar rol en Firebase", e);
        }

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findByMail(String mail){
        Usuario usuario = usuarioRepository.getUsuarioByMail(mail);
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findByUid(String uid){
        Usuario usuario = usuarioRepository.getUsuarioByUid(uid);
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByMail(String mail) {
        return usuarioRepository.existsByMail(mail);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUid(String uid) {
        return usuarioRepository.existsByUid(uid);
    }


}
