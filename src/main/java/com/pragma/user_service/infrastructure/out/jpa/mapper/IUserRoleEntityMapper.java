package com.pragma.user_service.infrastructure.out.jpa.mapper;

import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IUserRoleEntityMapper {

    @Mapping(target = "id", source = "userRoleEntity.id")
    UserRole toUserRole(UserRoleEntity userRoleEntity);

}
