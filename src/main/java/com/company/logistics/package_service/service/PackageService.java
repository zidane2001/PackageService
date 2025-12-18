package com.company.logistics.package_service.service;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;

public interface PackageService {

    PackageResponseDto create(PackageRequestDto request);
}
