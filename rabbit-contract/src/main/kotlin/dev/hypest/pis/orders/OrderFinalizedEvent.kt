package dev.hypest.pis.orders

import dev.hypest.pis.common.eventaggregator.DomainEvent
import java.math.BigDecimal
import java.util.UUID

data class OrderFinalizedEvent(
    val orderId: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val amount: BigDecimal, // java.money?
    val deliveryDetails: DeliveryDetails
) : DomainEvent(
    aggregateId = orderId
) {
    data class DeliveryDetails(
        val address: String
    )

    data class OrderItem(
        val productId: UUID,
        var quantity: Int
    )

}
