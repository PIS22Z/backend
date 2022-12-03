package dev.hypest.pis.restaurants.domain.restaurants

import jakarta.inject.Singleton
import java.util.UUID

interface CreateRestaurantHandler {
    fun create(command: CreateRestaurantCommand): UUID
}

@Singleton
class CreateRestaurantHandlerImpl(
    private val restaurantRepository: RestaurantRepository
) : CreateRestaurantHandler {
    override fun create(command: CreateRestaurantCommand): UUID {
        val restaurant = Restaurant.new(
            name = command.name,
            logoUrl = command.logoUrl,
            description = command.description,
            products = emptyList(),
            city = command.city,
            street = command.street,
            number = command.number,
        )
        restaurantRepository.add(restaurant)
        return restaurant.id
    }
}

data class CreateRestaurantCommand(
    val name: String,
    val logoUrl: String,
    val description: String,
    val city: String,
    val street: String,
    val number: String,
)
