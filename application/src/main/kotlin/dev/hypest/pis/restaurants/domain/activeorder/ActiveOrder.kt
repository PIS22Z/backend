package dev.hypest.pis.restaurants.domain.activeorder

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent
import java.util.UUID

data class ActiveOrder(
    val id: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val deliveryDetails: DeliveryDetails,
    val isConfirmed: Boolean,
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
                isConfirmed = false,
                isReadyToDeliver = false
            )
        }
    }

    fun markAsReadyToDeliver() {
        check(!isReadyToDeliver) { "Order is already ready to deliver" }

        check(isConfirmed) { "Order is not confirmed" }

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
}
