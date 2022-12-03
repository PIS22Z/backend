@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.mapper

import dev.hypest.pis.restaurants.domain.restaurants.CreateRestaurantCommand
import dev.hypest.pis.restaurants.domain.restaurants.UpdateRestaurantCommand
import dev.hypest.pis.restaurants.restaurants.CreateRestaurantRequest
import dev.hypest.pis.restaurants.restaurants.UpdateRestaurantRequest
import java.util.UUID

object RestaurantMapper {
    fun mapToCreateRestaurantCommand(
        request: CreateRestaurantRequest
    ) = CreateRestaurantCommand(
        name = request.name,
        description = request.description,
        logoUrl = request.logoUrl,
        city = request.city,
        street = request.street,
        number = request.number,
    )

    fun mapToUpdateRestaurantCommand(
        restaurantId: UUID,
        request: UpdateRestaurantRequest
    ) = UpdateRestaurantCommand(
        restaurantId = restaurantId,
        name = request.name,
        description = request.description,
        logoUrl = request.logoUrl,
        city = request.city,
        street = request.street,
        number = request.number,
    )
}
