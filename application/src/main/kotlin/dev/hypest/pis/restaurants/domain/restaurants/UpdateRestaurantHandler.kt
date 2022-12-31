package dev.hypest.pis.restaurants.domain.restaurants

import jakarta.inject.Singleton
import java.util.UUID

interface UpdateRestaurantHandler {
    fun update(command: UpdateRestaurantCommand): UUID
}

@Singleton
class UpdateRestaurantHandlerImpl(
    private val restaurantRepository: RestaurantRepository,
) : UpdateRestaurantHandler {

    override fun update(command: UpdateRestaurantCommand): UUID {
        val restaurant = restaurantRepository.load(command.restaurantId)
            ?: throw RestaurantNotFoundException(command.restaurantId)
        val newRestaurant = restaurant.update(
            name = command.name,
            logoUrl = command.logoUrl,
            description = command.description,
            city = command.city,
            street = command.street,
            number = command.number,
        )
        restaurantRepository.save(newRestaurant)
        return restaurant.id
    }
}

data class UpdateRestaurantCommand(
    val restaurantId: UUID,
    val name: String?,
    val logoUrl: String?,
    val description: String?,
    val city: String?,
    val street: String?,
    val number: String?,
)
