package dev.hypest.pis.restaurants.domain.restaurants

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.RestaurantTestProvider
import jakarta.inject.Inject

class CreateRestaurantHandlerTest extends BaseTest {

    @Inject
    private CreateRestaurantHandler handler

    @Inject
    private RestaurantRepository productRepository

    def "given nothing, when valid restaurant is created, then it should be saved in db"() {
        given:
        def command = RestaurantTestProvider.getCreateRestaurantCommand()

        when:
        def id = handler.create(command)

        then:
        def savedRestaurant = productRepository.load(id)
        savedRestaurant != null
        savedRestaurant.id == id
        savedRestaurant.name == command.name
        savedRestaurant.logoUrl == command.logoUrl
        savedRestaurant.description == command.description
        savedRestaurant.city == command.city
        savedRestaurant.street == command.street
        savedRestaurant.number == command.number
    }

}
