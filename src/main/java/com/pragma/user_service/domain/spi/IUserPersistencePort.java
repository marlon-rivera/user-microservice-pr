package com.pragma.user_service.domain.spi;

import com.pragma.user_service.domain.model.Auth;
import com.pragma.user_service.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    Optional<User> findById(Long id);
    Auth authenticateUser(String email, String password);
}
