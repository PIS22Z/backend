package dev.hypest.pis.restaurants.domain.restaurants

import jakarta.inject.Singleton
import java.util.UUID

interface RemoveRestaurantHandler {
    fun remove(restaurantId: UUID)
}

@Singleton
class RemoveRestaurantHandlerImpl(
    private val restaurantRepository: RestaurantRepository
) : RemoveRestaurantHandler {
    override fun remove(restaurantId: UUID) {
        val restaurant = restaurantRepository.load(restaurantId) ?: throw RestaurantNotFoundException(restaurantId)
        restaurantRepository.remove(restaurant.id)
    }
}
