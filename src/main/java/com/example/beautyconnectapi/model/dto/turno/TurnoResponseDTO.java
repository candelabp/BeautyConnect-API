package com.example.beautyconnectapi.model.dto.turno;

import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TurnoResponseDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    private ClienteResponseDTO clienteResponseDTO;

    private ServicioResponseDTO servicioResponseDTO;

    private ProfesionalResponseDTO profesionalResponseDTO;

    private Estado estado;

}
