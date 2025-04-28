package com.pragma.user_service.domain.spi;

import com.pragma.user_service.domain.model.Auth;
import com.pragma.user_service.domain.model.User;

public interface IUserPersistencePort {

    void saveUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    Auth authenticateUser(String email, String password);
}
