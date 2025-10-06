package com.example.beautyconnectapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
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
public class HorarioCentro extends Base {
    private DayOfWeek dia;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaMInicio;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaMFinalizacion;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaTInicio;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaTFinalizacion;

}
