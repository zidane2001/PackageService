package com.company.logistics.package_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.logistics.package_service.entity.PacKage;
public interface PackageRepository extends JpaRepository<PacKage, Long> {

}
