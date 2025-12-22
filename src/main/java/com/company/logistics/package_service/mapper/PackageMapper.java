package com.company.logistics.package_service.mapper;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;
import com.company.logistics.package_service.entity.Package;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    @Mapping(target = "id", ignore = true)
    Package toEntity(PackageRequestDto request);

    PackageResponseDto toDto(Package entity);


    default Page<PackageResponseDto> toDtoPage(Page<Package> entities) {
        return entities.map(this::toDto);
    }

}
