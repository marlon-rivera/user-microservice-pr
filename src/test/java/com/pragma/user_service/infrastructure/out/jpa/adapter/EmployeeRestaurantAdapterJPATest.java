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


}