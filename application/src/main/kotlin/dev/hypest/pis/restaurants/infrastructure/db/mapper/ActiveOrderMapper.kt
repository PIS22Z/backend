package dev.hypest.pis.restaurants.infrastructure.db.mapper

import dev.hypest.pis.restaurants.domain.activeorder.ActiveOrder
import dev.hypest.pis.restaurants.domain.activeorder.DeliveryDetails
import dev.hypest.pis.restaurants.domain.activeorder.OrderItem
import dev.hypest.pis.restaurants.infrastructure.db.activeorder.ActiveOrderEntity
import dev.hypest.pis.restaurants.infrastructure.db.activeorder.DeliveryDetails as DbDeliveryDetails
import dev.hypest.pis.restaurants.infrastructure.db.activeorder.OrderItem as DbOrderItem

object ActiveOrderMapper {

    fun mapToActiveOrderEntity(activeOrder: ActiveOrder): ActiveOrderEntity = ActiveOrderEntity(
        id = activeOrder.id,
        restaurantId = activeOrder.restaurantId,
        userId = activeOrder.userId,
        items = activeOrder.items.map { DbOrderItem(it.productId, it.quantity) },
        deliveryDetails = DbDeliveryDetails(activeOrder.deliveryDetails.address),
        isConfirmed = activeOrder.isConfirmed,
        isReadyToDeliver = activeOrder.isReadyToDeliver
    )

    fun mapToActiveOrder(activeOrderEntity: ActiveOrderEntity): ActiveOrder = ActiveOrder(
        id = activeOrderEntity.id,
        restaurantId = activeOrderEntity.restaurantId,
        userId = activeOrderEntity.userId,
        items = activeOrderEntity.items.map { OrderItem(it.productId, it.quantity) },
        deliveryDetails = DeliveryDetails(activeOrderEntity.deliveryDetails.address),
        isConfirmed = activeOrderEntity.isConfirmed,
        isReadyToDeliver = activeOrderEntity.isReadyToDeliver
    )
}

