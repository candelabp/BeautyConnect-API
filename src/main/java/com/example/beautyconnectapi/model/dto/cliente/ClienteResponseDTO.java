package com.example.beautyconnectapi.model.dto.cliente;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteResponseDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private Integer telefono;

    private UsuarioResponseDTO usuario;
}
