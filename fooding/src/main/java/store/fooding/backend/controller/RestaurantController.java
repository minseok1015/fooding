package store.fooding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.fooding.backend.model.Restaurant;
import store.fooding.backend.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }
}
