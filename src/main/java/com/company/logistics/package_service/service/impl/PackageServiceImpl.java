package com.company.logistics.package_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.company.logistics.package_service.entity.Package;
import com.company.logistics.package_service.exceptions.PackageNotFoundException;
import com.company.logistics.package_service.repository.PackageRepository;
import com.company.logistics.package_service.service.PackageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

   private static final String PACKAGE_NOT_FOUND_MESSAGE = "Package not found with id: ";

   private final PackageRepository packageRepository;

   @Override
   @Transactional
   public Package create(Package entity) {
      return packageRepository.save(entity);
   }

   @Override
   public Package getById(Long id) {
      return packageRepository.findById(id)
            .orElseThrow(() -> new PackageNotFoundException(PACKAGE_NOT_FOUND_MESSAGE + id));
   }

   @Override
   public Page<Package> getAll(Pageable pageable) {
      return packageRepository.findAll(pageable);
   }

   @Override
   @Transactional
   public Package update(Package entity) {
      if (!packageRepository.existsById(entity.getId())) {
         throw new PackageNotFoundException(PACKAGE_NOT_FOUND_MESSAGE + entity.getId());
      }
      return packageRepository.save(entity);
   }

   @Override
   @Transactional
   public void delete(Long id) {
      if (!packageRepository.existsById(id)) {
         throw new PackageNotFoundException(PACKAGE_NOT_FOUND_MESSAGE + id);
      }
      packageRepository.deleteById(id);
   }
}