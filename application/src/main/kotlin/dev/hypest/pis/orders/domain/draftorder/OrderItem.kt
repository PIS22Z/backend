package dev.hypest.pis.orders.domain.draftorder

import java.util.UUID

data class OrderItem(
    val productId: UUID,
    val quantity: Int
)
