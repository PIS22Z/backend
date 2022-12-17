package dev.hypest.pis.restaurants

import dev.hypest.pis.common.eventaggregator.DomainEvent
import java.util.UUID

data class OrderReadyToDeliverEvent(
    val orderId: UUID,
    val restaurantId: UUID,
    val deliveryDetails: DeliveryDetails
) : DomainEvent(
    aggregateId = orderId
) {
    data class DeliveryDetails(
        val address: String
    )
}
