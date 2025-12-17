package com.company.logistics.package_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Package")
public class PacKage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "weight")
    @NotBlank(message = "Weight is mandatory")
    @Size(max = 10000)
    private Double weight;

    @Column(name = "fragile")
    private Boolean isFragile;

    // status is a type enum
    @Column(name = "status")
    @NotBlank(message = "Status is mandatory")
    PackageStatus status ;

}
