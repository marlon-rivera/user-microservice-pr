package com.pragma.user_service.domain.spi;

public interface IJWTPort {

    String getToken(String username, String role);

}
