package com.example.beautyconnectapi.model.dto.prestadorDeServicio;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrestadorDeServicioDTO {
    private String nombre;

    private Integer telefono;

    private UsuarioDTO usuario;
}
