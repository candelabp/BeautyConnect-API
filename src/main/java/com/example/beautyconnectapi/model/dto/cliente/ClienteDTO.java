package com.example.beautyconnectapi.model.dto.cliente;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.entity.Domicilio;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteDTO {
    private String nombre;

    private String apellido;

    private String telefono;

    private UsuarioDTO usuario;

}
