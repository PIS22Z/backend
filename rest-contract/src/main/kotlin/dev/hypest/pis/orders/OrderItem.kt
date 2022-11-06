package dev.hypest.pis.orders

import java.util.UUID

data class OrderItem(
    val productId: UUID,
    val quantity: Int
)
