package dev.hypest.pis.payments

import dev.hypest.pis.common.eventaggregator.DomainEvent
import java.util.UUID

data class OrderPaidEvent(
    val orderId: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val deliveryDetails: DeliveryDetails
) : DomainEvent(
    aggregateId = orderId
) {
    data class OrderItem(
        val productId: UUID,
        var quantity: Int
    )

    data class DeliveryDetails(
        val address: String
    )
}
