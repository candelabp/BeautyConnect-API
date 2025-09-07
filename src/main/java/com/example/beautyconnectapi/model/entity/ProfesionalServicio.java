package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class ProfesionalServicio extends Base{
    private Integer duracion;

    @ManyToOne
    private Servicio servicio;

    @ManyToOne
    private Profesional profesional;

    @OneToMany
    private List<Disponibilidad> disponibilidades;
}
