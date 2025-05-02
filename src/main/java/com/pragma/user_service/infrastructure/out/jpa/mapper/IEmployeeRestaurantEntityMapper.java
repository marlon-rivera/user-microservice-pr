package com.pragma.user_service.infrastructure.out.jpa.mapper;

import com.pragma.user_service.domain.model.EmployeeRestaurant;
import com.pragma.user_service.infrastructure.out.jpa.entity.EmployeeRestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IEmployeeRestaurantEntityMapper {

    @Mapping(target = "employeeRestaurantId", source = "employeeRestaurant.employeeRestaurantId")
    EmployeeRestaurantEntity toEntity(EmployeeRestaurant employeeRestaurant);

    EmployeeRestaurant toDomain(EmployeeRestaurantEntity employeeRestaurantEntity);

    default Optional<EmployeeRestaurant> toOptionalDomain(Optional<EmployeeRestaurantEntity> employeeRestaurantEntity){
        return employeeRestaurantEntity.map(this::toDomain);
    }

}
