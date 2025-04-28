package com.pragma.user_service.infrastructure.out.jpa.mapper;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

    default Optional<User> toOptionalUser(Optional<UserEntity> userEntityOptional) {
        return userEntityOptional.map(this::toUser);
    }

}
