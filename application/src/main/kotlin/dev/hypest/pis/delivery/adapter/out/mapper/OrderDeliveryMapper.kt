package dev.hypest.pis.delivery.adapter.out.mapper

import dev.hypest.pis.delivery.domain.orderdelivery.DeliveryDetails
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDelivery
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.OrderDeliveryEntity
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.DeliveryDetails as DbDeliveryDetails

object OrderDeliveryMapper {

    fun mapToOrderDeliveryEntity(orderDelivery: OrderDelivery): OrderDeliveryEntity =
        OrderDeliveryEntity(
            id = orderDelivery.id,
            restaurantId = orderDelivery.restaurantId,
            deliveryDetails = DbDeliveryDetails(
                address = orderDelivery.deliveryDetails.address,
            ),
            assignedCourierId = orderDelivery.assignedCourierId,
            isBeingDelivered = orderDelivery.isBeingDelivered
        )

    fun mapToOrderDelivery(orderDeliveryEntity: OrderDeliveryEntity): OrderDelivery =
        OrderDelivery(
            id = orderDeliveryEntity.id,
            restaurantId = orderDeliveryEntity.restaurantId,
            deliveryDetails = DeliveryDetails(
                address = orderDeliveryEntity.deliveryDetails.address,
            ),
            assignedCourierId = orderDeliveryEntity.assignedCourierId,
            isBeingDelivered = orderDeliveryEntity.isBeingDelivered
        )
}
