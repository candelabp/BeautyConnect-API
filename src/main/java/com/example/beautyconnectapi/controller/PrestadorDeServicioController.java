package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prestadordeservicio")
public class PrestadorDeServicioController {
    @Autowired
    private PrestadorDeServicioService prestadorDeServicioService;
}
