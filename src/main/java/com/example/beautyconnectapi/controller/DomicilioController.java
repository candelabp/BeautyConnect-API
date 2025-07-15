package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/domicilio")
public class DomicilioController {
    @Autowired
    private DomicilioService domicilioService;
}
