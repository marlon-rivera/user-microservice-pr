package com.pragma.user_service.infrastructure.feign.adapter;

import com.pragma.user_service.domain.spi.IRestaurantPersistencePort;
import com.pragma.user_service.infrastructure.feign.client.IRestaurantFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantAdapterFeign implements IRestaurantPersistencePort {

    private final IRestaurantFeignClient restaurantFeignClient;

    @Override
    public boolean validateOwnerRestaurant(Long restaurantId) {
        return restaurantFeignClient.validateOwnerRestaurant(restaurantId);
    }
}
