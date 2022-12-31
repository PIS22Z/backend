@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.query

import dev.hypest.pis.restaurants.restaurants.RestaurantResponse
import java.util.UUID

interface RestaurantQuery {
    fun getRestaurants(): List<RestaurantResponse>
    fun getRestaurant(restaurantId: UUID): RestaurantResponse
}
