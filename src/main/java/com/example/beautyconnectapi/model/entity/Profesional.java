package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.TipoDeServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Profesional extends Base{
    private String nombre;

    private String apellido;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Disponibilidad> disponibilidades;

    @ElementCollection
    @CollectionTable(name = "profesional_servicios", joinColumns = @JoinColumn(name = "profesional_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "servicio")
    private Set<TipoDeServico> servicios = new HashSet<>();

}
