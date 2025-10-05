package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.TipoDeServicio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class Servicio extends Base {
    @Enumerated(EnumType.STRING)
    private TipoDeServicio tipoDeServicio;

    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centro_id", nullable = false)
    private CentroDeEstetica centroDeEstetica;


}
