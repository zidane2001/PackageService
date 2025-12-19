package com.company.logistics.package_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "weight")
    @NotNull(message = "Weight is mandatory")
    @Positive(message = "Weight must be positive")
    @Max(value = 10000, message = "Weight must not exceed 10000")
    private Double weight;

    @Column(name = "fragile")
    private Boolean isFragile;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is mandatory")
    private PackageStatus status;

}