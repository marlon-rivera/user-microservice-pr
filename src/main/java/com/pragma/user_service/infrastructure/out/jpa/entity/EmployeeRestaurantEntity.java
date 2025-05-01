package com.pragma.user_service.infrastructure.out.jpa.entity;

import com.pragma.user_service.domain.model.EmployeeRestaurantId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeRestaurantEntity {

    @EmbeddedId
    private EmployeeRestaurantId employeeRestaurantId;

}