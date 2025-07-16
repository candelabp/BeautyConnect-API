package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ClienteMapper;
import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.entity.Cliente;
import com.example.beautyconnectapi.model.enums.Rol;
import com.example.beautyconnectapi.repository.ClienteRepository;
import com.example.beautyconnectapi.service.ClienteService;
import org.springframework.stereotype.Service;

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
    public ClienteResponseDTO registrarCliente(ClienteDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.getUsuario().setRol(Rol.CLIENTE);
        return clienteMapper.toResponseDto(clienteRepo.save(cliente));
    }

    @Override
    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepo.findAll().stream()
                .map(clienteMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.toResponseDto(cliente);

    }
}
