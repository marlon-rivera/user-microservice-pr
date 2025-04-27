package com.pragma.user_service.domain.api;

import com.pragma.user_service.domain.model.User;

public interface IUserServicePort {

    void saveOwner(User user);
    boolean isOwner(Long userId);
}
