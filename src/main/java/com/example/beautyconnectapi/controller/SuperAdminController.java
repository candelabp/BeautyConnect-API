package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {
    @Autowired
    private SuperAdminService superAdminService;
}
