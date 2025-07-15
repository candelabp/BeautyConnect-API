package com.example.beautyconnectapi.model.dto.superAdmin;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SuperAdminDTO {
    private String nombre;

    private Integer telefono;

    private UsuarioDTO usuario;
}
