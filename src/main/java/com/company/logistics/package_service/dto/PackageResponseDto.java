package com.company.logistics.package_service.dto;

import com.company.logistics.package_service.common.PackageStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponseDto {
    private Long id;
    private String description;
    private Double weight;
    private Boolean isFragile;
    private PackageStatus status;
}
