package com.example.beautyconnectapi.model.dto.superAdmin;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SuperAdminResponseDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private String telefono;

    private UsuarioResponseDTO usuario;
}
