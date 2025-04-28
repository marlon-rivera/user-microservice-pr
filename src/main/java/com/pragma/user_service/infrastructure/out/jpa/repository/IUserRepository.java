package com.pragma.user_service.infrastructure.out.jpa.repository;

import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);

    Optional<UserEntity> findByEmail(String email);

}
