package dev.hypest.pis.restaurants.domain

import java.math.BigDecimal
import java.util.UUID

data class Product(
    val id: UUID,
    val restaurantId: UUID,
    val name: String,
    val photoUrl: String,
    val price: BigDecimal
)
