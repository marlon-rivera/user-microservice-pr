package com.pragma.user_service.domain.api;

import com.pragma.user_service.domain.model.UserRole;

public interface IUserRoleServicePort {

    UserRole getRoleByName(String name);

}
