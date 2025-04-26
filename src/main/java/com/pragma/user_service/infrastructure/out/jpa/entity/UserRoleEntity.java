package com.pragma.user_service.infrastructure.out.jpa.entity;

import com.pragma.user_service.infrastructure.out.jpa.util.constants.user.role.UserRoleEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = UserRoleEntityConstants.USER_ROLE_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = UserRoleEntityConstants.USER_ROLE_NAME_COLUMN_NAME, nullable = false, unique = true)
    private String name;
    @OneToMany
    @JoinColumn(name = UserRoleEntityConstants.USER_ROLE_ID_COLUMN)
    List<UserEntity> users;

}
