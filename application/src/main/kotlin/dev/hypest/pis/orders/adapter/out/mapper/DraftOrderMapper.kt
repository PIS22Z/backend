package dev.hypest.pis.orders.adapter.out.mapper

import dev.hypest.pis.orders.domain.draftorder.DraftOrder
import dev.hypest.pis.orders.infrastructure.db.draftorder.DraftOrderEntity
import dev.hypest.pis.orders.infrastructure.db.draftorder.OrderItem
import dev.hypest.pis.orders.domain.draftorder.OrderItem as DomainOrderItem

object DraftOrderMapper {

    fun mapToDraftOrderEntity(draftOrder: DraftOrder): DraftOrderEntity = DraftOrderEntity(
        id = draftOrder.id,
        restaurantId = draftOrder.restaurantId,
        userId = draftOrder.userId,
        items = draftOrder.items.map {
            OrderItem(
                it.productId, it.quantity
            )
        },
        isFinalized = draftOrder.isFinalized
    )

    fun mapToDraftOrder(draftOrderEntity: DraftOrderEntity): DraftOrder = DraftOrder(
        id = draftOrderEntity.id,
        restaurantId = draftOrderEntity.restaurantId,
        userId = draftOrderEntity.userId,
        items = draftOrderEntity.items.map {
            DomainOrderItem(
                it.productId, it.quantity
            )
        }.toMutableList(),
        isFinalized = draftOrderEntity.isFinalized
    )
}

