package com.pragma.user_service.infrastructure.out.jpa.repository;

import com.pragma.user_service.domain.model.EmployeeRestaurantId;
import com.pragma.user_service.infrastructure.out.jpa.entity.EmployeeRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, EmployeeRestaurantId> {
}
