package dev.hypest.pis.restaurants.restaurants

import dev.hypest.pis.restaurants.product.ProductResponse
import java.util.UUID

data class RestaurantResponse(
    val id: UUID,
    val name: String,
    val logoUrl: String,
    val description: String,
    val products: List<ProductResponse>,
    val street: String,
    val number: String,
    val city: String,
)
