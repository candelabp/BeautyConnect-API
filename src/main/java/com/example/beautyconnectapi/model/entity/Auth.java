package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.enums.Rol;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Auth {
    private String idToken;

    private String mail;

    private Rol rol;

    private String uid;

    private ClienteDTO clienteDTO;

    private PrestadorDeServicioDTO prestadorDTO;
}
