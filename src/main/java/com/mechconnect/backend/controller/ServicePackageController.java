package com.mechconnect.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mechconnect.backend.dto.CreatePackageRequest;
import com.mechconnect.backend.dto.ServicePackageResponseDto;
import com.mechconnect.backend.entity.ServicePackage;
import com.mechconnect.backend.entity.enums.PackageType;
import com.mechconnect.backend.service.ServicePackageService;

import java.util.List;

@RestController
@RequestMapping("/api/packages")

public class ServicePackageController {

    private final ServicePackageService servicePackageService;

    public ServicePackageController(ServicePackageService servicePackageService) {
        this.servicePackageService = servicePackageService;
    }
    
    // ✅ Create Package
    @PostMapping
    public ResponseEntity<ServicePackageResponseDto> createPackage(
            @RequestBody CreatePackageRequest request) {

        ServicePackageResponseDto savedPackage = servicePackageService.createPackage(request);
        return ResponseEntity.ok(savedPackage);
    }
    
    
    // ✅ Get All Active Packages
    @GetMapping
    public ResponseEntity<List<ServicePackageResponseDto>> getAllPackages() {
        return ResponseEntity.ok(servicePackageService.getAllActivePackages());
    }

    // ✅ Get Packages By Type (ADMIN / MECHANIC / CUSTOMER)
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ServicePackageResponseDto>> getByType(
            @PathVariable PackageType type) {

        return ResponseEntity.ok(servicePackageService.getPackagesByType(type));
    }

    // ✅ Get Packages Created By Specific User
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<ServicePackageResponseDto>> getByCreator(
            @PathVariable Long creatorId) {

        return ResponseEntity.ok(servicePackageService.getPackagesByCreator(creatorId));
    }

    // ✅ Deactivate Package
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {

        servicePackageService.deactivatePackage(id);
        return ResponseEntity.ok("Package deactivated successfully");
    }
}