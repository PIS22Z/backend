package dev.hypest.pis.restaurants.infrastructure.db.activeorder

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID

@MappedEntity("active_order")
data class ActiveOrderEntity(
    @field:Id
    val id: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val isConfirmed: Boolean
)
