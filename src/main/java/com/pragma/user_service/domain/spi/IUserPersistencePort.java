package com.pragma.user_service.domain.spi;

import com.pragma.user_service.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    Optional<User> findById(Long id);

}
