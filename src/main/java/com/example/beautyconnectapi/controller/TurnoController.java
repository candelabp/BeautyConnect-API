package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
}
