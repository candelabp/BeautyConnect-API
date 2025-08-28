package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;

import java.util.List;


public interface ClienteService {
    ClienteResponseDTO registrarCliente(ClienteDTO dto);
    List<ClienteResponseDTO> listarClientes();
    ClienteResponseDTO obtenerPorId(Long id);
    ClienteResponseDTO actualizarCliente(Long id, ClienteDTO dto);
    ClienteResponseDTO findByUsuarioId(Long id);
    ClienteResponseDTO obtenerPorUid(String uid);
    ClienteResponseDTO cambiarEstadoActive(Long id);
}
