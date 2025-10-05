package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PrestadorDeServicio extends Base {
    private String nombre;

    private String apellido;

    private String telefono;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

}
