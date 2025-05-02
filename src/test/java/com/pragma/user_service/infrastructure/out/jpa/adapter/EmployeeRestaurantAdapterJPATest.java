package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.EmployeeRestaurant;
import com.pragma.user_service.domain.model.EmployeeRestaurantId;
import com.pragma.user_service.infrastructure.out.jpa.entity.EmployeeRestaurantEntity;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IEmployeeRestaurantEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IEmployeeRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeRestaurantAdapterJPATest {

    @Mock
    private IEmployeeRestaurantRepository employeeRestaurantRepository;

    @Mock
    private IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;

    @InjectMocks
    private EmployeeRestaurantAdapterJPA employeeRestaurantAdapterJPA;

    private EmployeeRestaurant employeeRestaurant;
    private EmployeeRestaurantEntity employeeRestaurantEntity;

    @BeforeEach
    void setUp() {
        employeeRestaurant = new EmployeeRestaurant();
        EmployeeRestaurantId employeeRestaurantId = new EmployeeRestaurantId();
        employeeRestaurantId.setEmployeeId(1L);
        employeeRestaurantId.setRestaurantId(1L);
        employeeRestaurant.setEmployeeRestaurantId(employeeRestaurantId);
        employeeRestaurantEntity = new EmployeeRestaurantEntity();
        employeeRestaurantEntity.setEmployeeRestaurantId(employeeRestaurantId);
    }

    @Test
    void saveEmployeeRestaurant_ShouldSaveEmployeeRestaurant() {
        when(employeeRestaurantEntityMapper.toEntity(employeeRestaurant)).thenReturn(employeeRestaurantEntity);

        employeeRestaurantAdapterJPA.saveEmployeeRestaurant(employeeRestaurant);

        verify(employeeRestaurantEntityMapper).toEntity(employeeRestaurant);
        verify(employeeRestaurantRepository).save(employeeRestaurantEntity);
    }

    @Test
    void getEmployeeRestaurantByIdEmployee_ShouldReturnEmployeeRestaurant() {
        // Arrange
        Long employeeId = 1L;
        Optional<EmployeeRestaurantEntity> optionalEmployeeRestaurantEntity = Optional.of(employeeRestaurantEntity);
        Optional<EmployeeRestaurant> expectedEmployeeRestaurant = Optional.of(employeeRestaurant);

        when(employeeRestaurantRepository.findByEmployeeId(employeeId)).thenReturn(optionalEmployeeRestaurantEntity);
        when(employeeRestaurantEntityMapper.toOptionalDomain(optionalEmployeeRestaurantEntity)).thenReturn(expectedEmployeeRestaurant);

        // Act
        Optional<EmployeeRestaurant> result = employeeRestaurantAdapterJPA.getEmployeeRestaurantByIdEmployee(employeeId);

        // Assert
        assertEquals(expectedEmployeeRestaurant, result);
        verify(employeeRestaurantRepository).findByEmployeeId(employeeId);
        verify(employeeRestaurantEntityMapper).toOptionalDomain(optionalEmployeeRestaurantEntity);
    }

    @Test
    void getEmployeeRestaurantByIdEmployee_WhenNotFound_ShouldReturnEmptyOptional() {
        // Arrange
        Long employeeId = 999L;
        Optional<EmployeeRestaurantEntity> emptyOptionalEntity = Optional.empty();
        Optional<EmployeeRestaurant> emptyOptionalDomain = Optional.empty();

        when(employeeRestaurantRepository.findByEmployeeId(employeeId)).thenReturn(emptyOptionalEntity);
        when(employeeRestaurantEntityMapper.toOptionalDomain(emptyOptionalEntity)).thenReturn(emptyOptionalDomain);

        // Act
        Optional<EmployeeRestaurant> result = employeeRestaurantAdapterJPA.getEmployeeRestaurantByIdEmployee(employeeId);

        // Assert
        assertTrue(result.isEmpty());
        verify(employeeRestaurantRepository).findByEmployeeId(employeeId);
        verify(employeeRestaurantEntityMapper).toOptionalDomain(emptyOptionalEntity);
    }

}