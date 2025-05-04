package com.pragma.user_service.domain.api;

import com.pragma.user_service.domain.model.Auth;
import com.pragma.user_service.domain.model.User;

public interface IUserServicePort {

    void saveOwner(User user);
    boolean isOwner(Long userId);
    Auth login(String email, String password);
    void saveEmployee(User user, Long restaurantId);
    void saveClient(User user);
    Long getIdRestaurantByIdEmployee();
    String getPhoneNumberByIdClient(Long idClient);
}
