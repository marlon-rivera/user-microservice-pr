package com.pragma.user_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRestaurantId {
    private Long employeeId;
    private Long restaurantId;
}