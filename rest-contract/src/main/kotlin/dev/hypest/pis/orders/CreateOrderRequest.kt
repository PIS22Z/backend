package dev.hypest.pis.orders

import java.util.UUID

data class CreateOrderRequest(
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>
)
