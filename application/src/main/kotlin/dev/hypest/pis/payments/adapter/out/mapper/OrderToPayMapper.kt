package dev.hypest.pis.payments.adapter.out.mapper

import dev.hypest.pis.payments.domain.ordertopay.OrderToPay
import dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderToPayEntity
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails as DomainDeliveryDetails
import dev.hypest.pis.payments.infrastructure.db.ordertopay.DeliveryDetails as DbDeliveryDetails

object OrderToPayMapper {

    fun mapToOrderToPayEntity(orderToPay: OrderToPay): OrderToPayEntity = OrderToPayEntity(
        id = orderToPay.id,
        userId = orderToPay.userId,
        amount = orderToPay.amount,
        deliveryDetails = DbDeliveryDetails(
            address = orderToPay.deliveryDetails.address
        ),
        isPaid = orderToPay.isPaid
    )

    fun mapToOrderToPay(orderToPayEntity: OrderToPayEntity): OrderToPay = OrderToPay(
        id = orderToPayEntity.id,
        userId = orderToPayEntity.userId,
        amount = orderToPayEntity.amount,
        deliveryDetails = DomainDeliveryDetails(
            address = orderToPayEntity.deliveryDetails.address
        ),
        isPaid = orderToPayEntity.isPaid
    )
}
