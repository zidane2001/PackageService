package com.company.logistics.package_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;
import com.company.logistics.package_service.service.PackageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @PostMapping
    public ResponseEntity<PackageResponseDto> create(@Valid @RequestBody PackageRequestDto request) {
        PackageResponseDto createdPackage = packageService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPackage);
    }

}