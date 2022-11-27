package dev.hypest.pis.orders.domain.draftorder

import java.util.UUID

data class ModifyOrderItemCommand(
    val orderId: UUID,
    val product: OrderItem
)
