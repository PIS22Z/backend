@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.payments.adapter.`in`.mapper

import dev.hypest.pis.orders.OrderFinalizedEvent
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails

object OrderFinalizedEventMapper {

    fun mapToCreateOrderToPayCommand(event: OrderFinalizedEvent): CreateOrderToPayCommand {
        return CreateOrderToPayCommand(
            orderId = event.aggregateId,
            userId = event.userId,
            amount = event.amount,
            deliveryDetails = DeliveryDetails(
                address = event.deliveryDetails.address
            )
        )
    }
}
