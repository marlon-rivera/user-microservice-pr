package com.pragma.user_service.domain.spi;

public interface IRestaurantPersistencePort {

    boolean validateOwnerRestaurant(Long restaurantId);

}
