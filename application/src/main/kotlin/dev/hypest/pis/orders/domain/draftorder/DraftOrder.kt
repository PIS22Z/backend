package dev.hypest.pis.orders.domain.draftorder

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import dev.hypest.pis.orders.OrderFinalizedEvent
import dev.hypest.pis.orders.domain.restaurants.OrdersToRestaurantsPort
import java.math.BigDecimal
import java.util.UUID

data class DraftOrder(
    val id: UUID,
    val userId: UUID,
    val items: MutableList<OrderItem>,
    var isFinalized: Boolean
) : AggregateRoot() {
    companion object {

        @JvmStatic
        fun new(userId: UUID, items: List<OrderItem>): DraftOrder {
            return DraftOrder(
                id = UUID.randomUUID(),
                userId = userId,
                items = items.toMutableList(),
                isFinalized = false
            )
        }
    }

    fun addItem(productId: UUID, quantity: Int) {
        check(!isFinalized) { "Cannot add item to finalized order" }
        items.firstOrNull { it.productId == productId }?.let {
            it.quantity += quantity
        } ?: run {
            items.add(OrderItem(productId, quantity))
        }
    }

    fun removeItem(productId: UUID, quantity: Int) {
        check(!isFinalized) { "Cannot remove item from finalized order" }
        items.firstOrNull { it.productId == productId }?.let {
            it.quantity -= quantity
            if (it.quantity <= 0) {
                items.remove(it)
            }
        }
    }

    fun finalize(command: FinalizeOrderCommand, restaurantsPort: OrdersToRestaurantsPort) {
        check(!isFinalized) { "Order is already finalized" }

        var amount = BigDecimal.ZERO
        items.forEach { item ->
            val product = restaurantsPort.getProduct(item.productId)
            checkNotNull(product) { "Product with ID=${item.productId} not found" }
            amount += product.price * item.quantity.toBigDecimal()
        }

        isFinalized = true
        publishOrderFinalizedEvent(amount, command.deliveryDetails)
    }

    private fun publishOrderFinalizedEvent(amount: BigDecimal, deliveryDetails: DeliveryDetails) {
        publishEvent(
            OrderFinalizedEvent(
                id,
                userId,
                amount,
                OrderFinalizedEvent.DeliveryDetails(deliveryDetails.address)
            )
        )
    }
}
