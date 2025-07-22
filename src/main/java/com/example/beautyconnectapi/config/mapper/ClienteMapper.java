package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "usuario.mail", target = "mail")
    @Mapping(source = "usuario.contrasenia", target = "contrasenia")
    Cliente toEntity(ClienteDTO dto);

    @Mapping(source = "usuario.mail", target = "mail")
    ClienteResponseDTO toResponseDto(Cliente cliente);
}
