package dev.hypest.pis.orders

import java.util.UUID

data class CreateOrderRequest(
    val userId: UUID,
    val items: List<OrderItem>
)
