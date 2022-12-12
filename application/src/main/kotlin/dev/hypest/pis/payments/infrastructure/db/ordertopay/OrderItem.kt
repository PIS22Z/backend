package dev.hypest.pis.payments.infrastructure.db.ordertopay

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class OrderItem(
    val productId: UUID,
    var quantity: Int
)
