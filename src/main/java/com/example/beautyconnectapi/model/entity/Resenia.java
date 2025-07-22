package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class Resenia extends Base {
    private Integer puntuacion;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;
    @ManyToOne
    private CentroDeEstetica centroDeEstetica;
}
