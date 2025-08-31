package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.TipoDeServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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
    @JoinTable(
            name = "profesional_disponibilidades",
            joinColumns = @JoinColumn(name = "profesional_id"),
            inverseJoinColumns = @JoinColumn(name = "disponibilidades_id")
    )
    private List<Disponibilidad> disponibilidades = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "profesional_servicios", joinColumns = @JoinColumn(name = "profesional_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "servicio")
    private Set<TipoDeServico> servicios = new HashSet<>();

}
