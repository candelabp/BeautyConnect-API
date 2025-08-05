package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.model.enums.TipoDeServico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Turno extends Base {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private TipoDeServico tipoDeservicio;

    @OneToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;

    @Enumerated(EnumType.STRING)
    private Estado estado;



}
