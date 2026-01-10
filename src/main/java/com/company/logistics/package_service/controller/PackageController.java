package com.company.logistics.package_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;
import com.company.logistics.package_service.entity.Packages;
import com.company.logistics.package_service.mapper.PackageMapper;
import com.company.logistics.package_service.service.PackageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;
    private final PackageMapper packageMapper;

    @PostMapping
    public ResponseEntity<PackageResponseDto> create(@Valid @RequestBody PackageRequestDto request) {
        Packages entity = packageMapper.toEntity(request);
        Packages createdEntity = packageService.create(entity);
        PackageResponseDto responseDto = packageMapper.toDto(createdEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDto> getById(@PathVariable Long id) {
        Packages entity = packageService.getById(id);
        PackageResponseDto responseDto = packageMapper.toDto(entity);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<PackageResponseDto>> getAll(Pageable pageable) {
        Page<Packages> entities = packageService.getAll(pageable);
        return ResponseEntity.ok(entities.map(packageMapper::toDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDto> update(@PathVariable Long id,
            @Valid @RequestBody PackageRequestDto request) {
        Packages entity = packageMapper.toEntity(request);
        Packages updatedEntity = packageService.update(id, entity);
        PackageResponseDto responseDto = packageMapper.toDto(updatedEntity);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        packageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}