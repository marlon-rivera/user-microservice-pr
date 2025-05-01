package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.EmployeeRestaurant;
import com.pragma.user_service.domain.spi.IEmployeeRestaurantPersistencePort;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IEmployeeRestaurantEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IEmployeeRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRestaurantAdapterJPA implements IEmployeeRestaurantPersistencePort {

    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
    private final IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;


    @Override
    public void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant) {
        employeeRestaurantRepository.save(
                employeeRestaurantEntityMapper.toEntity(employeeRestaurant)
        );
    }
}
