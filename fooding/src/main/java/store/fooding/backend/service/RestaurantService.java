package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.fooding.backend.model.Restaurant;
import store.fooding.backend.repository.RestaurantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
