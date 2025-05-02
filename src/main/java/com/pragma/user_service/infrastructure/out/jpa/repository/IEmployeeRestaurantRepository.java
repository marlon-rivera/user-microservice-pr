package com.pragma.user_service.infrastructure.out.jpa.repository;

import com.pragma.user_service.domain.model.EmployeeRestaurantId;
import com.pragma.user_service.infrastructure.out.jpa.entity.EmployeeRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, EmployeeRestaurantId> {

    @Query(value = "SELECT * FROM employee_restaurant er WHERE er.employee_id =:employeeId", nativeQuery = true)
    Optional<EmployeeRestaurantEntity> findByEmployeeId(Long employeeId);

}
