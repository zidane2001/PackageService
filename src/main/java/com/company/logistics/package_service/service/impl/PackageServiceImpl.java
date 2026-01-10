package com.company.logistics.package_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.company.logistics.package_service.entity.Packages;
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
   public Packages create(Packages entity) {
      return packageRepository.save(entity);
   }

   @Override
   public Packages getById(Long id) {
      return packageRepository.findById(id)
            .orElseThrow(() -> new PackageNotFoundException(PACKAGE_NOT_FOUND_MESSAGE + id));
   }

   @Override
   public Page<Packages> getAll(Pageable pageable) {
      return packageRepository.findAll(pageable);
   }

   @Override
   @Transactional
   public Packages update(Long id, Packages entity) {
      entity.setId(id);
      if (!packageRepository.existsById(id)) {
         throw new PackageNotFoundException(PACKAGE_NOT_FOUND_MESSAGE + id);
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