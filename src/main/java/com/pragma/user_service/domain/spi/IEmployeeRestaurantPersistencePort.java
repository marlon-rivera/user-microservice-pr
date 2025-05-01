package com.pragma.user_service.domain.spi;

import com.pragma.user_service.domain.model.EmployeeRestaurant;

public interface IEmployeeRestaurantPersistencePort {

    void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant);

}
