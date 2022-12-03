package dev.hypest.pis.restaurants.domain.restaurants

import jakarta.inject.Singleton
import java.util.UUID

interface DeleteRestaurantHandler {
    fun delete(restaurantId: UUID)
}

@Singleton
class DeleteRestaurantHandlerImpl(
    private val restaurantRepository: RestaurantRepository
) : DeleteRestaurantHandler {
    override fun delete(restaurantId: UUID) {
        val restaurant = restaurantRepository.load(restaurantId) ?: throw RestaurantNotFoundException(restaurantId)
        restaurantRepository.delete(restaurant.id)
    }
}
