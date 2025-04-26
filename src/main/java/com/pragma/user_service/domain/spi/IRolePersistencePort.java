package com.pragma.user_service.domain.spi;

import com.pragma.user_service.domain.model.UserRole;

public interface IRolePersistencePort {

    UserRole getRoleByName(String name);

}
