package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicio")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;
}
