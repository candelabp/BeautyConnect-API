package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class HorarioCentro extends Base{
    private DayOfWeek diaDesde;
    private DayOfWeek diaHasta;
    private LocalTime horaInicio;
    private LocalTime horaFinalizacion;
}
