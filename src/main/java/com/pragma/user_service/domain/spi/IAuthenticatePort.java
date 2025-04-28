package com.pragma.user_service.domain.spi;

public interface IAuthenticatePort {

    void authenticateUser(String username, String password);

}
