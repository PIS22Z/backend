package dev.hypest.pis.restaurants.domain.activeorder

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent
import dev.hypest.pis.restaurants.OrderRejectedEvent
import java.util.UUID

data class ActiveOrder(
    val id: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val deliveryDetails: DeliveryDetails,
    var isAccepted: Boolean?,
    var isReadyToDeliver: Boolean
) : AggregateRoot() {

    companion object {
        @JvmStatic
        fun new(
            id: UUID,
            restaurantId: UUID,
            userId: UUID,
            items: List<OrderItem>,
            deliveryDetails: DeliveryDetails
        ): ActiveOrder {
            return ActiveOrder(
                id = id,
                restaurantId = restaurantId,
                userId = userId,
                items = items,
                deliveryDetails = deliveryDetails,
                isAccepted = null,
                isReadyToDeliver = false
            )
        }
    }

    fun accept() {
        check(isAccepted == null) { "Order is already either accepted or rejected" }

        isAccepted = true
    }

    fun reject() {
        check(isAccepted == null) { "Order is already either accepted or rejected" }

        isAccepted = false
        publishOrderRejectedEvent()
    }

    fun markAsReadyToDeliver() {
        check(!isReadyToDeliver) { "Order is already ready to deliver" }

        check(isAccepted ?: false) { "Order is not accepted" }

        isReadyToDeliver = true

        publishOrderReadyToDeliverEvent()
    }

    private fun publishOrderReadyToDeliverEvent() {
        publishEvent(
            OrderReadyToDeliverEvent(
                orderId = id,
                restaurantId = restaurantId,
                deliveryDetails = OrderReadyToDeliverEvent.DeliveryDetails(
                    address = deliveryDetails.address
                )
            )
        )
    }

    private fun publishOrderRejectedEvent() {
        publishEvent(
            OrderRejectedEvent(
                orderId = id,
            )
        )
    }
}
