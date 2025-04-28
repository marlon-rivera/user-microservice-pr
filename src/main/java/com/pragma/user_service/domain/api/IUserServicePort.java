package com.pragma.user_service.domain.api;

import com.pragma.user_service.domain.model.Auth;
import com.pragma.user_service.domain.model.User;

public interface IUserServicePort {

    void saveOwner(User user);
    Auth login(String email, String password);
}
