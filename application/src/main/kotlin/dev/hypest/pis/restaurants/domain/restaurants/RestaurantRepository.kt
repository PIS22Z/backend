package dev.hypest.pis.restaurants.domain.restaurants

import java.util.UUID

interface RestaurantRepository {
    fun add(restaurant: Restaurant)
    fun load(id: UUID): Restaurant?
    fun save(restaurant: Restaurant)
    fun remove(id: UUID)
}
