package dev.hypest.pis.payments.domain.ordertopay

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import dev.hypest.pis.payments.OrderPaidEvent
import java.math.BigDecimal
import java.util.UUID

data class OrderToPay(
    val id: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val amount: BigDecimal,
    val deliveryDetails: DeliveryDetails,
    var isPaid: Boolean,
    var isRefunded: Boolean,
) : AggregateRoot() {

    fun pay() {
        check(!isRefunded) { "Order is already refunded" }
        check(!isPaid) { "Order is already paid" }
        isPaid = true
        publishOrderPaidEvent()
    }

    fun refund() {
        check(!isRefunded) { "Order is already refunded" }
        check(isPaid) { "Order is not paid" }
        isPaid = false
        isRefunded = true
    }

    companion object {
        @Suppress("LongParameterList")
        @JvmStatic
        fun new(
            id: UUID,
            restaurantId: UUID,
            userId: UUID,
            items: List<OrderItem>,
            amount: BigDecimal,
            deliveryDetails: DeliveryDetails
        ): OrderToPay {
            return OrderToPay(
                id = id,
                restaurantId = restaurantId,
                userId = userId,
                items = items,
                amount = amount,
                deliveryDetails = deliveryDetails,
                isPaid = false,
                isRefunded = false,
            )
        }
    }

    private fun publishOrderPaidEvent() {
        publishEvent(
            OrderPaidEvent(
                orderId = id,
                restaurantId = restaurantId,
                userId = userId,
                items = items.map { OrderPaidEvent.OrderItem(it.productId, it.quantity) },
                deliveryDetails = OrderPaidEvent.DeliveryDetails(deliveryDetails.address)
            )
        )
    }
}
