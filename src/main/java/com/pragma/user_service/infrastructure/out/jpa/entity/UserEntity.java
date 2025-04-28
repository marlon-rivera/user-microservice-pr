package com.pragma.user_service.infrastructure.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pragma.user_service.infrastructure.out.jpa.util.constants.user.UserEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = UserEntityConstants.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserEntityConstants.ID_COLUMN)
    private Long id;
    @Column(
            name = UserEntityConstants.NAME_COLUMN,
            length = UserEntityConstants.NAME_LENGTH,
            nullable = false)
    private String name;
    @Column(
            name = UserEntityConstants.LAST_NAME_COLUMN,
            length = UserEntityConstants.LAST_NAME_LENGTH,
            nullable = false)
    private String lastName;
    @Column(
            name = UserEntityConstants.DNI_COLUMN,
            length = UserEntityConstants.DNI_LENGTH,
            nullable = false,
            unique = true)
    private String dni;
    @Column(
            name = UserEntityConstants.PHONE_NUMBER_COLUMN,
            length = UserEntityConstants.PHONE_NUMBER_LENGTH,
            nullable = false)
    private String phoneNumber;
    @Column(
            name = UserEntityConstants.DATE_OF_BIRTH_COLUMN,
            nullable = false)
    private String dateOfBirth;
    @Column(
            name = UserEntityConstants.EMAIL_COLUMN,
            length = UserEntityConstants.EMAIL_LENGTH,
            nullable = false,
            unique = true)
    private String email;
    @Column(
            name = UserEntityConstants.PASSWORD_COLUMN,
            nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = UserEntityConstants.USER_ROLE_ID_COLUMN,
            nullable = false)
    @JsonIgnore
    private UserRoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return id.toString();
    }
}
