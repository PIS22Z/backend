package dev.hypest.pis.orders.domain.draftorder

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import dev.hypest.pis.orders.OrderFinalizedEvent
import java.math.BigDecimal
import java.util.UUID

data class DraftOrder(
    val id: UUID,
    val userId: UUID,
    val items: MutableList<OrderItem>,
    var isFinalized: Boolean,
    var amount: BigDecimal?
) : AggregateRoot() {
    companion object {

        @JvmStatic
        fun new(userId: UUID, items: List<OrderItem>): DraftOrder {
            return DraftOrder(
                id = UUID.randomUUID(),
                userId = userId,
                items = items.toMutableList(),
                isFinalized = false,
                amount = null
            )
        }
    }

    fun addItem(productId: UUID, quantity: Int) {
        check(!isFinalized) { "Cannot add item to finalized order" }
        items.add(OrderItem(productId, quantity))
    }

    fun finalize(amount: BigDecimal) {
        isFinalized = true
        this.amount = amount
        publishOrderFinalizedEvent()
    }

    private fun publishOrderFinalizedEvent() {
        publishEvent(OrderFinalizedEvent(id, userId, amount!!))
    }
}
