package dev.hypest.pis.payments.adapter.out.mapper

import dev.hypest.pis.payments.domain.ordertopay.OrderToPay
import dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderToPayEntity

object OrderToPayMapper {

    fun mapToOrderToPayEntity(orderToPay: OrderToPay): OrderToPayEntity = OrderToPayEntity(
        id = orderToPay.id,
        userId = orderToPay.userId,
        amount = orderToPay.amount,
        isPaid = orderToPay.isPaid
    )

    fun mapToOrderToPay(orderToPayEntity: OrderToPayEntity): OrderToPay = OrderToPay(
        id = orderToPayEntity.id,
        userId = orderToPayEntity.userId,
        amount = orderToPayEntity.amount,
        isPaid = orderToPayEntity.isPaid
    )
}
