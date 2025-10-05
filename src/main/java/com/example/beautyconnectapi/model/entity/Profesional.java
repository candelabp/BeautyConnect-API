package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Profesional extends Base{
    private String nombre;

    private String apellido;
    private Long contacto;

    @ManyToOne
    private CentroDeEstetica centroDeEstetica;

}
