@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.mapper

import dev.hypest.pis.payments.OrderPaidEvent
import dev.hypest.pis.restaurants.domain.activeorder.CreateActiveOrderCommand
import dev.hypest.pis.restaurants.domain.activeorder.OrderItem

object OrderPaidEventMapper {

    fun mapToCreateActiveOrderCommand(event: OrderPaidEvent): CreateActiveOrderCommand {
        return CreateActiveOrderCommand(
            orderId = event.aggregateId,
            restaurantId = event.restaurantId,
            userId = event.userId,
            items = event.items.map {
                OrderItem(
                    it.productId,
                    it.quantity
                )
            }
        )
    }
}
