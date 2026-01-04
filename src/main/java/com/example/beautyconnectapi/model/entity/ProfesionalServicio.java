package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@Table(
        name = "profesional_servicio",
        uniqueConstraints = @UniqueConstraint(columnNames = {"profesional_id","servicio_id"})
)
public class ProfesionalServicio extends Base {
    private Integer duracion;

    @ManyToOne private Servicio servicio;
    @ManyToOne private Profesional profesional;
}
