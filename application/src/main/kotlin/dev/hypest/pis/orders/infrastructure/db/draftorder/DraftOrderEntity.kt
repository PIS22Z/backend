package dev.hypest.pis.orders.infrastructure.db.draftorder

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID

@MappedEntity("draft_order")
data class DraftOrderEntity(
    @field:Id
    val id: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val isFinalized: Boolean
)
