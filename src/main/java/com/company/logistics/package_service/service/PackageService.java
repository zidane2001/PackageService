package com.company.logistics.package_service.service;

import com.company.logistics.package_service.dto.request.CreatePackageRequest;
import com.company.logistics.package_service.entity.PacKage;

public interface PackageService {

 PacKage createPackage(CreatePackageRequest request);
}

