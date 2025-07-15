package com.example.beautyconnectapi.model.dto.cliente;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteDTO {
    private String nombre;

    private String apellido;

    private Integer telefono;

    private UsuarioDTO usuario;
}
