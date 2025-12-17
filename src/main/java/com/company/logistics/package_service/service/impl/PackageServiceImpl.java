package com.company.logistics.package_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.logistics.package_service.dto.request.CreatePackageRequest;
import com.company.logistics.package_service.entity.PacKage;
import com.company.logistics.package_service.mapper.PackageMapper;
import com.company.logistics.package_service.repository.PackageRepository;
import com.company.logistics.package_service.service.PackageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    @Autowired
    private final PackageRepository packageRepository;

 
    // Describe the implementation of the createPackage method with comments :
    // The createPackage method is used to create a new package.
    // The method takes a CreatePackageRequest object as a parameter.
    // The method uses the PackageMapper class to convert the request object to the entity object.
    // The method uses the packageRepository class to save the entity object.
    // The method returns the saved entity object.

    @Override
     public PacKage createPackage(CreatePackageRequest request) {
        PacKage entity = PackageMapper.toEntity(request);
        return packageRepository.save(entity);
     }
}
