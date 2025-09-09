package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, DomicilioMapper.class})
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteDTO dto);
    ClienteResponseDTO toResponseDto(Cliente cliente);
}
