package com.pragma.user_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private UserRole role;

}
