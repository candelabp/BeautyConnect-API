package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.entity.Cliente;

public interface ClienteMapper {
    Cliente toEntity(ClienteDTO clienteDTO);
    ClienteResponseDTO toResponseDTO(Cliente cliente);
}
