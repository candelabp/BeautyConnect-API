package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.TipoDeServico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.ManyToOne;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Servicio extends Base {
    @Enumerated(EnumType.STRING)
    private TipoDeServico tipoDeServico;

    private Integer duracion;

    private Double precio;

    @ManyToOne
    private CentroDeEstetica centroDeEstetica;
}
