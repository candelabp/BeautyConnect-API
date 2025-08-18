package com.example.beautyconnectapi.model.dto.usuario;

import com.example.beautyconnectapi.model.enums.Rol;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {
    private String mail;

    private Rol rol;

    private String uid;
}
