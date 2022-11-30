package dev.hypest.pis.restaurants.domain.activeorder

import java.util.UUID

data class OrderItem(
    val productId: UUID,
    var quantity: Int
)
