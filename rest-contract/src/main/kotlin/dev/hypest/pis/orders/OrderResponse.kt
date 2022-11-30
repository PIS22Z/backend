package dev.hypest.pis.orders

import java.math.BigDecimal
import java.util.UUID

data class OrderResponse(
    val id: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val amount: BigDecimal?,
    val deliveryDetails: DeliveryDetails?,
    val isFinalized: Boolean,
    val isPaid: Boolean,
    val isConfirmed: Boolean
) {
    data class OrderItem(
        val productId: UUID,
        val quantity: Int
    )

    data class DeliveryDetails(
        val address: String
    )
}
