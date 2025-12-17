package com.company.logistics.package_service.dto.request;
import com.company.logistics.package_service.entity.PackageStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePackageRequest {
    // Dto for package
    private String description;

    private Double weight;

    private Boolean isfragile;

    private PackageStatus status;

}
