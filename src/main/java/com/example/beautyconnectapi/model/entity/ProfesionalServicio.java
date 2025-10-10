package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

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
