package com.pragma.user_service.infrastructure.out.jpa.repository;

import com.pragma.user_service.infrastructure.out.jpa.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByName(String name);

}
