package com.company.logistics.package_service.service;

import com.company.logistics.package_service.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PackageService {
    Package create(Package entity);

    Package getById(Long id);

    Page<Package> getAll(Pageable pageable);

    Package update(Package entity);

    void delete(Long id);
}