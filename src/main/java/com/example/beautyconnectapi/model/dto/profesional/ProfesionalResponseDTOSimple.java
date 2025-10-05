package com.example.beautyconnectapi.model.dto.profesional;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalResponseDTOSimple {
    private Long id;
    private Boolean active;

    private String nombre;
    private Long contacto;


    private String apellido;
}
