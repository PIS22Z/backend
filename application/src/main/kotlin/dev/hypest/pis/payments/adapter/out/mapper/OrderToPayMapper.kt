package dev.hypest.pis.payments.adapter.out.mapper

import dev.hypest.pis.payments.domain.ordertopay.OrderItem
import dev.hypest.pis.payments.domain.ordertopay.OrderToPay
import dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderToPayEntity
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails as DomainDeliveryDetails
import dev.hypest.pis.payments.infrastructure.db.ordertopay.DeliveryDetails as DbDeliveryDetails
import dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderItem as DbOrderItem

object OrderToPayMapper {

    fun mapToOrderToPayEntity(orderToPay: OrderToPay): OrderToPayEntity = OrderToPayEntity(
        id = orderToPay.id,
        restaurantId = orderToPay.restaurantId,
        userId = orderToPay.userId,
        items = orderToPay.items.map { DbOrderItem(it.productId, it.quantity) },
        amount = orderToPay.amount,
        deliveryDetails = DbDeliveryDetails(
            address = orderToPay.deliveryDetails.address
        ),
        isPaid = orderToPay.isPaid
    )

    fun mapToOrderToPay(orderToPayEntity: OrderToPayEntity): OrderToPay = OrderToPay(
        id = orderToPayEntity.id,
        restaurantId = orderToPayEntity.restaurantId,
        userId = orderToPayEntity.userId,
        items = orderToPayEntity.items.map { OrderItem(it.productId, it.quantity) },
        amount = orderToPayEntity.amount,
        deliveryDetails = DomainDeliveryDetails(
            address = orderToPayEntity.deliveryDetails.address
        ),
        isPaid = orderToPayEntity.isPaid
    )
}
