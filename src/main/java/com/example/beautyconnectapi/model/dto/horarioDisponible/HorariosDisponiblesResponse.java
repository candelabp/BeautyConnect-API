package com.example.beautyconnectapi.model.dto.horarioDisponible;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorariosDisponiblesResponse {
    private LocalDate fecha;
    private Integer duracionMin;
    private List<String> inicios; // ["16:00","16:10","16:20",...]
}