package com.pragma.user_service.application.mapper;

import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserRequestDto userRequest);

}
