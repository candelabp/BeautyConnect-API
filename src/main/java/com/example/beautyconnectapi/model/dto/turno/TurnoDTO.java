package com.example.beautyconnectapi.model.dto.turno;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TurnoDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    private ClienteDTO cliente;
}
