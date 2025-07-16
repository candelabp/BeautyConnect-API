package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteService {
    ClienteResponseDTO registrarCliente(ClienteDTO dto);
    List<ClienteResponseDTO> listarClientes();
    ClienteResponseDTO obtenerPorId(Long id);
}
