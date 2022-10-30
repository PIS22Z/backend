package dev.hypest.pis.payments

import dev.hypest.pis.common.eventaggregator.DomainEvent
import java.util.UUID

// order is paid (can be sent to restaurant)
data class OrderPaidEvent(
    val orderId: UUID,
) : DomainEvent(
    aggregateId = orderId
)
