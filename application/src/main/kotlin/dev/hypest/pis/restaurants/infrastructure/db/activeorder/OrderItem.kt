package dev.hypest.pis.restaurants.infrastructure.db.activeorder

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class OrderItem(
    val productId: UUID,
    val quantity: Int
)
