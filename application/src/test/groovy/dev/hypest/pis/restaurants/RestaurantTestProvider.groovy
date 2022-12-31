package dev.hypest.pis.restaurants


import dev.hypest.pis.restaurants.domain.restaurants.CreateRestaurantCommand
import dev.hypest.pis.restaurants.domain.restaurants.Restaurant
import dev.hypest.pis.restaurants.domain.restaurants.UpdateRestaurantCommand

class RestaurantTestProvider {
    static CreateRestaurantCommand getCreateRestaurantCommand(Map map = [:]) {
        return new CreateRestaurantCommand(
                map.name ?: "name",
                map.logoUrl ?: "url",
                map.description ?: "description",
                map.city ?: "city",
                map.street ?: "street",
                map.number ?: "number"
        )
    }

    static UpdateRestaurantCommand getUpdateRestaurantCommand(Map map = [:]) {
        return new UpdateRestaurantCommand(
                map.id ?: UUID.randomUUID(),
                map.name ?: "name",
                map.logoUrl ?: "url",
                map.description ?: "description",
                map.city ?: "city",
                map.street ?: "street",
                map.number ?: "number"
        )
    }

    static Restaurant getAggregate(Map map = [:]) {
        return new Restaurant(
                map.id ?: UUID.randomUUID() as UUID,
                map.name ?: "name",
                map.logoUrl ?: "url",
                map.description ?: "description",
                map.products ?: [],
                map.city ?: "city",
                map.street ?: "street",
                map.number ?: "number"
        )
    }
}