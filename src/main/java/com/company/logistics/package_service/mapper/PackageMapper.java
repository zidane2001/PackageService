package com.company.logistics.package_service.mapper;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;
import com.company.logistics.package_service.entity.Packages;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PackageMapper {


  @Mapping(target = "id", ignore = true)
  Packages toEntity(PackageRequestDto request);

  PackageResponseDto toDto(Packages entity);

  List<PackageResponseDto> toDtoList(List<Packages> entities);
}
