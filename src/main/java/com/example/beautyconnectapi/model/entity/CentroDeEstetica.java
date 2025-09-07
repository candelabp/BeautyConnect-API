package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class CentroDeEstetica extends Base {
    private String nombre;

    private String descripcion;

    private String imagen;

    private String docValido;

    private Long cuit;

    @OneToOne
    private PrestadorDeServicio prestadoresServicio;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    @OneToMany(
            mappedBy = "centroDeEstetica",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Servicio> servicios = new ArrayList<>();

    @OneToMany
    private List<Resenia> resenias;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany
    private List <Profesional> profesionales;

    @OneToOne(cascade = CascadeType.PERSIST)
    private HorarioCentro horarioCentros;
}
