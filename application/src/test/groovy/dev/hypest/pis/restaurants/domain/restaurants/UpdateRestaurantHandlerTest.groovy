package dev.hypest.pis.restaurants.domain.restaurants

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.RestaurantTestProvider
import jakarta.inject.Inject

class UpdateRestaurantHandlerTest extends BaseTest {

    @Inject
    private UpdateRestaurantHandler handler

    @Inject
    private RestaurantRepository restaurantRepository

    def "given existing restaurant, when it is updated, then it should be updated in db"() {
        given:
        def existingRestaurant = RestaurantTestProvider.getAggregate(
            name: "existing restaurant",
            logoUrl: "existing logoUrl",
            description: "existing description",
            city: "existing city",
            street: "existing street",
            number: "existing number"
        )
        restaurantRepository.add(existingRestaurant)

        def command = RestaurantTestProvider.getUpdateRestaurantCommand(
            id: existingRestaurant.id,
            name: "updated restaurant",
            logoUrl: "updated logoUrl",
            description: "updated description",
        )

        when:
        def id = handler.update(command)

        then:
        def savedRestaurant = restaurantRepository.load(id)
        savedRestaurant != null
        savedRestaurant.id == id
        savedRestaurant.name == command.name
        savedRestaurant.logoUrl == command.logoUrl
        savedRestaurant.description == command.description
    }
}
