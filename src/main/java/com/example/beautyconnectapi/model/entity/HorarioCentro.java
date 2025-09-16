package com.example.beautyconnectapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.firebase.database.annotations.NotNull;
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

    @NotNull("El día desde es obligatorio")
    private DayOfWeek dia;

//    @NotNull("El día hasta es obligatorio")
//    private DayOfWeek diaHasta;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaMInicio;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaMFinalizacion;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaTInicio;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaTFinalizacion;

}
