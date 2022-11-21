package dev.hypest.pis.orders

import java.util.UUID

data class ModifyOrderItemRequest(
    val productId: UUID,
    val quantity: Int
)
