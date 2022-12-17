package dev.hypest.pis.delivery.adapter.`in`.mapper

import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.DeliveryDetails
import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent

object OrderReadyToDeliverEventMapper {

    fun mapToCreateOrderDeliveryCommand(event: OrderReadyToDeliverEvent): CreateOrderDeliveryCommand {
        return CreateOrderDeliveryCommand(
            orderId = event.aggregateId,
            restaurantId = event.restaurantId,
            deliveryDetails = DeliveryDetails(
                address = event.deliveryDetails.address
            )
        )
    }
}
