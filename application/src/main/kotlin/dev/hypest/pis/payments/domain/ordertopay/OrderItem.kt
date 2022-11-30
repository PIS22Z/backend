package dev.hypest.pis.payments.domain.ordertopay

import java.util.UUID

data class OrderItem(
    val productId: UUID,
    var quantity: Int
)
