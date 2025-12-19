package com.company.logistics.package_service.service;

import com.company.logistics.package_service.dto.PackageRequestDto;
import com.company.logistics.package_service.dto.PackageResponseDto;
import com.company.logistics.package_service.entity.Package;
import com.company.logistics.package_service.entity.PackageStatus;
import com.company.logistics.package_service.mapper.PackageMapper;
import com.company.logistics.package_service.repository.PackageRepository;
import com.company.logistics.package_service.service.impl.PackageServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

        @Mock
        private PackageRepository packageRepository;

        @Mock
        private PackageMapper packageMapper;

        @InjectMocks
        private PackageServiceImpl packageService;

        @Test
        void successfully_create() {
             
                PackageRequestDto request = new PackageRequestDto(
                                "Ordinateur portable",
                                2.5,
                                true,
                                PackageStatus.CREATED);

                Package entity = Package.builder()
                                .description("Ordinateur portable")
                                .weight(2.5)
                                .isFragile(true)
                                .status(PackageStatus.CREATED)
                                .build();

                Package savedEntity = Package.builder()
                                .id(1L)
                                .description("Ordinateur portable")
                                .weight(2.5)
                                .isFragile(true)
                                .status(PackageStatus.CREATED)
                                .build();

                PackageResponseDto expectedResponse = new PackageResponseDto(
                                1L,
                                "Ordinateur portable",
                                2.5,
                                true,
                                PackageStatus.CREATED);

             
                when(packageMapper.toEntity(request)).thenReturn(entity);
                when(packageRepository.save(entity)).thenReturn(savedEntity);
                when(packageMapper.toDto(savedEntity)).thenReturn(expectedResponse);

            
                PackageResponseDto result = packageService.create(request);

            
                assertNotNull(result);
                assertEquals(1L, result.getId());
                assertEquals("Ordinateur portable", result.getDescription());
                assertEquals(2.5, result.getWeight());
                assertTrue(result.getIsFragile());
                assertEquals(PackageStatus.CREATED, result.getStatus());

                verify(packageMapper, times(1)).toEntity(request);
                verify(packageRepository, times(1)).save(entity);
                verify(packageMapper, times(1)).toDto(savedEntity);
        }

        @Test
        void getById_successfully_returns_package() {
           
                Long packageId = 1L;
                Package entity = Package.builder()
                                .id(packageId)
                                .description("Ordinateur portable")
                                .weight(2.5)
                                .isFragile(true)
                                .status(PackageStatus.CREATED)
                                .build();

                PackageResponseDto expectedResponse = new PackageResponseDto(
                                packageId,
                                "Ordinateur portable",
                                2.5,
                                true,
                                PackageStatus.CREATED);

            
                when(packageRepository.findById(packageId)).thenReturn(Optional.of(entity));
                when(packageMapper.toDto(entity)).thenReturn(expectedResponse);

           
                PackageResponseDto result = packageService.getById(packageId);

               
                assertNotNull(result);
                assertEquals(packageId, result.getId());
                assertEquals("Ordinateur portable", result.getDescription());
                assertEquals(2.5, result.getWeight());
                assertTrue(result.getIsFragile());
                assertEquals(PackageStatus.CREATED, result.getStatus());

                verify(packageRepository, times(1)).findById(packageId);
                verify(packageMapper, times(1)).toDto(entity);
        }

        @Test
        void getById_throws_exception_when_package_not_found() {
                
                Long packageId = 999L;
                when(packageRepository.findById(packageId)).thenReturn(Optional.empty());

           
                RuntimeException exception = assertThrows(RuntimeException.class,
                                () -> packageService.getById(packageId));

                assertEquals("Package not found with id: 999", exception.getMessage());
                verify(packageRepository, times(1)).findById(packageId);
                verify(packageMapper, never()).toDto(any());
        }
}
