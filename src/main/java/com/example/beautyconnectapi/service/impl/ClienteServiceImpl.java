package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ClienteMapper;
import com.example.beautyconnectapi.exception.ResourceNotFoundException;
import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.entity.Cliente;
import com.example.beautyconnectapi.model.enums.Rol;
import com.example.beautyconnectapi.repository.ClienteRepository;
import com.example.beautyconnectapi.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepo;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepo, ClienteMapper clienteMapper) {
        this.clienteRepo = clienteRepo;
        this.clienteMapper = clienteMapper;
    }

    @Override
    @Transactional
    public ClienteResponseDTO registrarCliente(ClienteDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.getUsuario().setRol(Rol.CLIENTE);
        return clienteMapper.toResponseDto(clienteRepo.save(cliente));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepo.findAll().stream()
                .map(clienteMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        return clienteMapper.toResponseDto(cliente);

    }
    @Override
    @Transactional
    public ClienteResponseDTO actualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new  ResourceNotFoundException("Cliente", id));

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());

        Cliente guardado = clienteRepo.save(cliente);
        return clienteMapper.toResponseDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO findByUsuarioId(Long idUsuario) {
        Cliente cliente = clienteRepo.getByUsuarioId(idUsuario);
        return clienteMapper.toResponseDto(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorUid(String uid) {
        Cliente cliente = clienteRepo.findByUsuarioUid(uid)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente" , uid));
        return clienteMapper.toResponseDto(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO cambiarEstadoActive(Long id){
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        cliente.setActive(!cliente.getActive());
        cliente.getUsuario().setActive(cliente.getActive());
        return clienteMapper.toResponseDto(clienteRepo.save(cliente));
    }
}
