package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Profesional extends Base{
    private String nombre;

    @OneToMany
    private List<Disponibilidad> disponibilidades;

    @OneToMany
    private List<Servicio> servicios;
    @ManyToOne
    private CentroDeEstetica centroDeEstetica;
}
