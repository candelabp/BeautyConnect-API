package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Usuario extends Base {
    private String mail;

    private String uid;

    @Enumerated(EnumType.STRING)
    private Rol rol;

}
