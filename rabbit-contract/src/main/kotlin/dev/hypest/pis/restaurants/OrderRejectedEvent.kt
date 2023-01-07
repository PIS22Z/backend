package dev.hypest.pis.restaurants

import dev.hypest.pis.common.eventaggregator.DomainEvent
import java.util.UUID

class OrderRejectedEvent(
    val orderId: UUID,
) : DomainEvent(
    aggregateId = orderId
)
