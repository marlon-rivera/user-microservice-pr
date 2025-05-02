package com.pragma.user_service.domain.spi;

import com.pragma.user_service.domain.model.EmployeeRestaurant;

import java.util.Optional;

public interface IEmployeeRestaurantPersistencePort {

    void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant);
    Optional<EmployeeRestaurant> getEmployeeRestaurantByIdEmployee(Long id);

}
