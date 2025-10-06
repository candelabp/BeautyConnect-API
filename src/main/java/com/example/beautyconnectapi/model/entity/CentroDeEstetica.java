package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.Estado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    private PrestadorDeServicio prestadorDeServicio;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "centroDeEstetica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servicio> servicios = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HorarioCentro> horariosCentro;
}
