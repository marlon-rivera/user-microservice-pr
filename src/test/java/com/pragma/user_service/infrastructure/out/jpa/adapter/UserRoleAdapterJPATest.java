package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserRoleEntity;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserRoleEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRoleAdapterJPATest {

    @Mock
    private IUserRoleRepository userRoleRepository;

    @Mock
    private IUserRoleEntityMapper userRoleEntityMapper;

    @InjectMocks
    private UserRoleAdapterJPA userRoleAdapterJPA;

    private UserRoleEntity userRoleEntity;
    private UserRole userRole;
    private static final String ROLE_NAME = "ADMIN";

    @BeforeEach
    void setUp() {
        userRoleEntity = new UserRoleEntity();
        userRoleEntity.setId(1L);
        userRoleEntity.setName(ROLE_NAME);

        userRole = new UserRole();
        userRole.setId(1L);
        userRole.setName(ROLE_NAME);
    }

    @Test
    void getRoleByName_ShouldReturnUserRole() {
        when(userRoleRepository.findByName(ROLE_NAME)).thenReturn(userRoleEntity);
        when(userRoleEntityMapper.toUserRole(userRoleEntity)).thenReturn(userRole);

        UserRole result = userRoleAdapterJPA.getRoleByName(ROLE_NAME);

        assertNotNull(result);
        assertEquals(userRole.getId(), result.getId());
        assertEquals(userRole.getName(), result.getName());

        verify(userRoleRepository).findByName(ROLE_NAME);
        verify(userRoleEntityMapper).toUserRole(userRoleEntity);
    }

}