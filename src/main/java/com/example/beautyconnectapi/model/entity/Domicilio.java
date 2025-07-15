package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Domicilio extends Base {
    private String calle;

    private Integer numero;

    private String localidad;

    private Integer codigoPostal;

    @ManyToOne
    private CentroDeEstetica centroDeEstetica;
}
