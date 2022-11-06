@file:Suppress("InvalidPackageDeclaration")
package dev.hypest.pis.orders.adapter.`in`.mapper

import dev.hypest.pis.orders.CreateOrderRequest
import dev.hypest.pis.orders.FinalizeOrderRequest
import dev.hypest.pis.orders.domain.draftorder.CreateOrderCommand
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderCommand
import dev.hypest.pis.orders.domain.draftorder.OrderItem
import java.util.UUID

object DraftOrderMapper {

    fun mapToCreateOrderCommand(
        request: CreateOrderRequest
    ): CreateOrderCommand = CreateOrderCommand(
        userId = request.userId,
        items = request.items.map { OrderItem(it.productId, it.quantity) }
    )

    fun mapToFinalizeOrderCommand(
        orderId: UUID,
        request: FinalizeOrderRequest
    ): FinalizeOrderCommand = FinalizeOrderCommand(
        orderId = orderId,
        userId = request.userId
    )
}
