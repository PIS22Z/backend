package dev.hypest.pis.restaurants.domain.activeorder

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import java.util.UUID

data class ActiveOrder(
    val id: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val isConfirmed: Boolean
) : AggregateRoot() {

    companion object {
        @JvmStatic
        fun new(restaurantId: UUID, userId: UUID, items: List<OrderItem>): ActiveOrder {
            return ActiveOrder(
                id = UUID.randomUUID(),
                restaurantId = restaurantId,
                userId = userId,
                items = items,
                isConfirmed = false
            )
        }
    }
}
