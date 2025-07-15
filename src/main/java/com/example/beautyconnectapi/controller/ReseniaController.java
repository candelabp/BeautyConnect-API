package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.ReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resenia")
public class ReseniaController {
    @Autowired
    private ReseniaService reseniaService;
}
