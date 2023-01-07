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
    val status: OrderStatus
) {
    data class OrderItem(
        val productId: UUID,
        val quantity: Int
    )

    data class DeliveryDetails(
        val address: String
    )

    enum class OrderStatus {
        CREATED,
        FINALIZED,
        PAID,
        ACCEPTED,
        REJECTED,
        READY_TO_DELIVER,
        COURIER_ASSIGNED,
        DELIVERY_IN_PROGRESS,
        DELIVERED,
    }
}
