package com.company.logistics.package_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.logistics.package_service.entity.Packages;

public interface PackageRepository extends JpaRepository<Packages, Long> {

}
