package com.company.logistics.package_service.service;

import com.company.logistics.package_service.dto.request.CreatePackageRequest;
import com.company.logistics.package_service.entity.PacKage;
import com.company.logistics.package_service.entity.PackageStatus;
import com.company.logistics.package_service.repository.PackageRepository;
import com.company.logistics.package_service.service.impl.PackageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private PackageServiceImpl packageService;

    @Test
    void successfully_create() {
        // GIVEN : données d’entrée
        CreatePackageRequest request = new CreatePackageRequest(
                "Ordinateur portable",
                2.5,
                true,
                PackageStatus.CREATED
        );

        // Simulation du comportement du repository
        when(packageRepository.save(any(PacKage.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN : appel de la méthode testée
        PacKage result = packageService.createPackage(request);

        // THEN : vérifications
        assertNotNull(result);
        assertEquals("Ordinateur portable", result.getDescription());
        assertEquals(2.5, result.getWeight());
        assertTrue(result.getIsFragile());
        assertEquals(PackageStatus.CREATED, result.getStatus());

        verify(packageRepository, times(1))
                .save(any(PacKage.class));
    }
}
