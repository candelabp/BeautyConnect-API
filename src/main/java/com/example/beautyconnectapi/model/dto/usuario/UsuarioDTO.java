package com.example.beautyconnectapi.model.dto.usuario;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {
    private String mail;

    private String contrasenia;
}
