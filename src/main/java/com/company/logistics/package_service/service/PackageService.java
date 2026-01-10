package com.company.logistics.package_service.service;

import com.company.logistics.package_service.entity.Packages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PackageService {
    Packages create(Packages entity);

    Packages getById(Long id);

    Page<Packages> getAll(Pageable pageable);
    Packages update(Long id, Packages entity);

    void delete(Long id);
}