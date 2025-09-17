package com.example.beautyconnectapi.model.dto.usuario;

import com.example.beautyconnectapi.model.enums.Rol;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioResponseDTO {
    private Long id;

    private String mail;

    private Rol rol;

    private String uid;
}
