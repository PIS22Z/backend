package dev.hypest.pis.restaurants.domain.restaurants

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.RestaurantTestProvider
import jakarta.inject.Inject

class RemoveRestaurantHandlerTest extends BaseTest {

    @Inject
    private RemoveRestaurantHandler removeRestaurantHandler

    @Inject
    private RestaurantRepository restaurantRepository

    def "given existing restaurant, when it is removed, then it should be removed from db"() {
        given:
        def existingRestaurant = RestaurantTestProvider.getAggregate()
        restaurantRepository.add(existingRestaurant)

        when:
        removeRestaurantHandler.remove(existingRestaurant.id)

        then:
        restaurantRepository.load(existingRestaurant.id) == null
    }
}

