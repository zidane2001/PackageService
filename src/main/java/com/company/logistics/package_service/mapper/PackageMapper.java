package com.company.logistics.package_service.mapper;
import com.company.logistics.package_service.dto.request.CreatePackageRequest;
import com.company.logistics.package_service.entity.PacKage;

public class PackageMapper {

    private PackageMapper() {} // empty constructor in private ! to avoid instantiation of the class 

    // Describe the mapper class implementaion with builder pattern with comments :
    // The mapper class is used to convert the request object to the entity object.
    // The builder pattern is used to create the entity object.     

    public static PacKage toEntity(CreatePackageRequest request) {
        return PacKage.builder()
                .description(request.getDescription())
                .weight(request.getWeight())
                .isFragile(request.getIsfragile())
                .status(request.getStatus())
                .build();
    }

}
