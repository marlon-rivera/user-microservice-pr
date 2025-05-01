package com.pragma.user_service.infrastructure.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", url = "${feign.client.config.restaurant-service.url}")
public interface IRestaurantFeignClient {

    @GetMapping("/validate-owner/{restaurantId}")
    Boolean validateOwnerRestaurant(@PathVariable Long restaurantId);

}
