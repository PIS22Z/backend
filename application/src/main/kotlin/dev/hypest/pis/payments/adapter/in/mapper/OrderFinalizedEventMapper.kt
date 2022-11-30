@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.payments.adapter.`in`.mapper

import dev.hypest.pis.orders.OrderFinalizedEvent
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails
import dev.hypest.pis.payments.domain.ordertopay.OrderItem

object OrderFinalizedEventMapper {

    fun mapToCreateOrderToPayCommand(event: OrderFinalizedEvent): CreateOrderToPayCommand {
        return CreateOrderToPayCommand(
            orderId = event.aggregateId,
            restaurantId = event.restaurantId,
            userId = event.userId,
            items = event.items.map { OrderItem(it.productId, it.quantity) },
            amount = event.amount,
            deliveryDetails = DeliveryDetails(
                address = event.deliveryDetails.address
            )
        )
    }
}
