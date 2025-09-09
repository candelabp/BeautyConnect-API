package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.*;
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
public class Cliente extends Base{

    private String nombre;

    private String apellido;

    private String telefono;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

    @ManyToOne
    private Domicilio domicilio;
}
