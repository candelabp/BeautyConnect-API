package com.example.beautyconnectapi.model.dto.profesional;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalDTOSimple {
    private Long id;

    private String nombre;

    private String apellido;

}
