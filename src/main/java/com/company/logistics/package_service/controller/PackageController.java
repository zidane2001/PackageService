package com.company.logistics.package_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.logistics.package_service.dto.request.CreatePackageRequest;
import com.company.logistics.package_service.entity.PacKage;
import com.company.logistics.package_service.service.PackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    

    @PostMapping
    public ResponseEntity<PacKage> createPackage(@Valid @RequestBody CreatePackageRequest request) {
        PacKage createdPackage = packageService.createPackage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPackage);
    }


}