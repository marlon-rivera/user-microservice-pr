package com.pragma.user_service.infrastructure.out.jpa.mapper;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(User user);

}
