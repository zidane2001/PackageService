package com.company.logistics.package_service.service;

import com.company.logistics.package_service.common.PackageStatus;
import com.company.logistics.package_service.entity.Packages;
import com.company.logistics.package_service.exceptions.PackageNotFoundException;
import com.company.logistics.package_service.repository.PackageRepository;
import com.company.logistics.package_service.service.impl.PackageServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private PackageServiceImpl packageService;

    @Test
    void successfully_create() {

        Packages entity = Packages.builder()
                .description("Ordinateur portable")
                .weight(2.5)
                .isFragile(true)
                .status(PackageStatus.CREATED)
                .build();

        Packages savedEntity = Packages.builder()
                .id(1L)
                .description("Ordinateur portable")
                .weight(2.5)
                .isFragile(true)
                .status(PackageStatus.CREATED)
                .build();

        when(packageRepository.save(entity)).thenReturn(savedEntity);

        Packages result = packageService.create(entity);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ordinateur portable", result.getDescription());
        assertEquals(2.5, result.getWeight());
        assertTrue(result.getIsFragile());
        assertEquals(PackageStatus.CREATED, result.getStatus());

        verify(packageRepository, times(1)).save(entity);
    }

    @Test
    void getById_successfully_returns_package() {

        Long packageId = 1L;
        Packages entity = Packages.builder()
                .id(packageId)
                .description("Ordinateur portable")
                .weight(2.5)
                .isFragile(true)
                .status(PackageStatus.CREATED)
                .build();

        when(packageRepository.findById(packageId)).thenReturn(Optional.of(entity));

        Packages result = packageService.getById(packageId);

        assertNotNull(result);
        assertEquals(packageId, result.getId());
        assertEquals("Ordinateur portable", result.getDescription());
        assertEquals(2.5, result.getWeight());
        assertTrue(result.getIsFragile());
        assertEquals(PackageStatus.CREATED, result.getStatus());

        verify(packageRepository, times(1)).findById(packageId);
    }

    @Test
    void getById_throws_exception_when_package_not_found() {

        Long packageId = 999L;
        when(packageRepository.findById(packageId)).thenReturn(Optional.empty());

        PackageNotFoundException exception = assertThrows(PackageNotFoundException.class,
                () -> packageService.getById(packageId));

        assertEquals("Package not found with id: 999", exception.getMessage());
        verify(packageRepository, times(1)).findById(packageId);
    }

    @Test
    void getAll_returns_page_of_packages() {

        Pageable pageable = PageRequest.of(0, 10);
        Packages entity = Packages.builder()
                .id(1L)
                .description("Ordinateur portable")
                .weight(2.5)
                .isFragile(true)
                .status(PackageStatus.CREATED)
                .build();
        Page<Packages> page = new PageImpl<>(List.of(entity), pageable, 1);

        when(packageRepository.findAll(pageable)).thenReturn(page);

        Page<Packages> result = packageService.getAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(entity, result.getContent().get(0));

        verify(packageRepository, times(1)).findAll(pageable);
    }

    @Test
    void update_successfully_updates_package() {

        Packages entity = Packages.builder()
                .id(1L)
                .description("Ordinateur portable mis à jour")
                .weight(3.0)
                .isFragile(false)
                .status(PackageStatus.DELIVERED)
                .build();

        when(packageRepository.existsById(1L)).thenReturn(true);
        when(packageRepository.save(entity)).thenReturn(entity);

        Packages result = packageService.update(entity.getId(), entity);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ordinateur portable mis à jour", result.getDescription());
        assertEquals(3.0, result.getWeight());
        assertFalse(result.getIsFragile());
        assertEquals(PackageStatus.DELIVERED, result.getStatus());

        verify(packageRepository, times(1)).existsById(1L);
        verify(packageRepository, times(1)).save(entity);
    }

    @Test
    void update_throws_exception_when_package_not_found() {

        Packages entity = Packages.builder()
                .id(999L)
                .description("Ordinateur portable")
                .weight(2.5)
                .isFragile(true)
                .status(PackageStatus.CREATED)
                .build();

        when(packageRepository.existsById(999L)).thenReturn(false);

        PackageNotFoundException exception = assertThrows(PackageNotFoundException.class,
                () -> packageService.update(entity.getId(), entity));

        assertEquals("Package not found with id: 999", exception.getMessage());
        verify(packageRepository, times(1)).existsById(999L);
        verify(packageRepository, never()).save(any());
    }

    @Test
    void delete_successfully_deletes_package() {

        Long packageId = 1L;

        when(packageRepository.existsById(packageId)).thenReturn(true);

        packageService.delete(packageId);

        verify(packageRepository, times(1)).existsById(packageId);
        verify(packageRepository, times(1)).deleteById(packageId);
    }

    @Test
    void delete_throws_exception_when_package_not_found() {

        Long packageId = 999L;

        when(packageRepository.existsById(packageId)).thenReturn(false);

        PackageNotFoundException exception = assertThrows(PackageNotFoundException.class,
                () -> packageService.delete(packageId));

        assertEquals("Package not found with id: 999", exception.getMessage());
        verify(packageRepository, times(1)).existsById(packageId);
        verify(packageRepository, never()).deleteById(packageId);
    }
}