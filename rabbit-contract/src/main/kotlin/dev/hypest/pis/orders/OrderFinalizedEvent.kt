package dev.hypest.pis.orders

import dev.hypest.pis.common.eventaggregator.DomainEvent
import java.math.BigDecimal
import java.util.UUID

// order is finalised (closed, all data is ready, ready for payment)
data class OrderFinalizedEvent(
    val orderId: UUID,
    val userId: UUID,
    val amount: BigDecimal // java.money?
) : DomainEvent(
    aggregateId = orderId
)
