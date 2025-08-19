package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "images")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "name_image")
    private String name;


    @Column(name = "url_image")
    private String url;

    @Column(name = "public_id", unique = true, nullable = false)
    private String publicId;
}

