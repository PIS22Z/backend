package dev.hypest.pis.orders.infrastructure.db.draftorder

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class OrderItem(
    val productId: UUID,
    val quantity: Int
)
