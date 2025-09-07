package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminDTO;
import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminResponseDTO;
import com.example.beautyconnectapi.service.SuperAdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {
    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @PostMapping("/save")
    public SuperAdminResponseDTO saveSuperAdmin(@RequestBody SuperAdminDTO superAdminDTO){
        return superAdminService.saveSuperAdmin(superAdminDTO);
    }

    @GetMapping("/{superAdminId}")
    public SuperAdminResponseDTO getSuperAdminById(@PathVariable Long superAdminId){
        return superAdminService.getSuperAdminById(superAdminId);
    }

    @PatchMapping("/update/{superAdminId}")
    public SuperAdminResponseDTO updateSuperAdmin(@PathVariable Long superAdminId, SuperAdminDTO superAdminDTO){
        return superAdminService.updateSuperAdmin(superAdminId, superAdminDTO);
    }
}
