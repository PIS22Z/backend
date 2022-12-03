package dev.hypest.pis.restaurants.domain.restaurants

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.RestaurantTestProvider
import jakarta.inject.Inject

class DeleteRestaurantHandlerTest extends BaseTest {

    @Inject
    private DeleteRestaurantHandler deleteRestaurantHandler

    @Inject
    private RestaurantRepository restaurantRepository

    def "given existing restaurant, when it is deleted, then it should be deleted from db"() {
        given:
        def existingRestaurant = RestaurantTestProvider.getAggregate()
        restaurantRepository.add(existingRestaurant)

        when:
        deleteRestaurantHandler.delete(existingRestaurant.id)

        then:
        restaurantRepository.load(existingRestaurant.id) == null
    }
}

