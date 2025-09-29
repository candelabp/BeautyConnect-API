package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.firebase.FirebaseRoleService;
import com.example.beautyconnectapi.config.mapper.UsuarioMapper;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.model.entity.Usuario;
import com.example.beautyconnectapi.repository.UsuarioRepository;
import com.example.beautyconnectapi.service.UsuarioService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final FirebaseRoleService roleService;

    @Override
    @Transactional
    public UsuarioResponseDTO saveUsuario(UsuarioDTO usuarioDto) {
        // 1) Persisto en DB
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuarioRepository.save(usuario);

        // 2) Asigno claim en Firebase DESPUÉS del commit para evitar inconsistencias
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                try {
                    roleService.asignarRol(usuario.getUid(), usuarioDto.getRol().name());
                } catch (FirebaseAuthException e) {
                    // Logueá/encolá un reintento si querés manejarlo luego
                    // log.error("No se pudo asignar rol en Firebase al uid {}: {}", usuario.getUid(), e.getMessage());
                }
            }
        });

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO getUsuarioById(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO deleteUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActive(false); // soft delete
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findByMail(String mail) {
        // Si tu repo hoy devuelve null (getUsuarioByMail), manejamos nulo acá.
        Usuario usuario = usuarioRepository.getUsuarioByMail(mail);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado por mail: " + mail);
        }
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findByUid(String uid) {
        Usuario usuario = usuarioRepository.getUsuarioByUid(uid);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado por uid: " + uid);
        }
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
