package com.pragma.user_service.infrastructure.feign.adapter;

import com.pragma.user_service.infrastructure.feign.client.IRestaurantFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantAdapterFeignTest {

    @Mock
    private IRestaurantFeignClient restaurantFeignClient;

    @InjectMocks
    private RestaurantAdapterFeign restaurantAdapterFeign;

    public RestaurantAdapterFeignTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateOwnerRestaurant_ReturnsTrue() {
        Long restaurantId = 1L;
        when(restaurantFeignClient.validateOwnerRestaurant(restaurantId)).thenReturn(true);

        boolean result = restaurantAdapterFeign.validateOwnerRestaurant(restaurantId);

        assertTrue(result);
        verify(restaurantFeignClient).validateOwnerRestaurant(restaurantId);
    }

    @Test
    void validateOwnerRestaurant_ReturnsFalse() {
        Long restaurantId = 2L;
        when(restaurantFeignClient.validateOwnerRestaurant(restaurantId)).thenReturn(false);

        boolean result = restaurantAdapterFeign.validateOwnerRestaurant(restaurantId);

        assertFalse(result);
        verify(restaurantFeignClient).validateOwnerRestaurant(restaurantId);
    }

}