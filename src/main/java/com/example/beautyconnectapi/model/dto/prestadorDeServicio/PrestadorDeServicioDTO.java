package com.example.beautyconnectapi.model.dto.prestadorDeServicio;

import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrestadorDeServicioDTO {
    //@NotBlank(message = "El campo nombre no puede ser null ni estar vacio")
    private String nombre;

    //@NotBlank(message = "El campo apellido no puede ser null ni estar vacio")
    private String apellido;

    //@NotBlank(message = "El campo telefono no puede ser null ni estar vacio")
    private String telefono;

    //@NotNull(message = "El campo usuario no puede ser null")
    private UsuarioDTO usuario;

    private Boolean active;
}
