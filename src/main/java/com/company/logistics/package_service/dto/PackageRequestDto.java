package com.company.logistics.package_service.dto;

import com.company.logistics.package_service.common.PackageStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequestDto {
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Double weight;

    private Boolean isFragile;

    @NotNull
    private PackageStatus status;

}
