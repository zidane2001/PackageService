package com.company.logistics.package_service.service.impl;

import org.springframework.stereotype.Service;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;
import com.company.logistics.package_service.entity.Package;
import com.company.logistics.package_service.mapper.PackageMapper;
import com.company.logistics.package_service.repository.PackageRepository;
import com.company.logistics.package_service.service.PackageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

   private final PackageRepository packageRepository;
   private final PackageMapper packageMapper;

   @Override
   public PackageResponseDto create(PackageRequestDto request) {
      Package entity = packageMapper.toEntity(request);
      Package saved = packageRepository.save(entity);
      return packageMapper.toDto(saved);
   }
}
