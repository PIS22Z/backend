package dev.hypest.pis.payments

import dev.hypest.pis.payments.domain.ordertopay.*

class OrderToPayTestProvider {

    static CreateOrderToPayCommand getCreateOrderToPayCommand(Map map = [:]) {
        return new CreateOrderToPayCommand(
                map.orderId ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.items ?: [new OrderItem(UUID.randomUUID(), 1)],
                map.amount ?: BigDecimal.ZERO,
                map.deliveryDetails ?: new DeliveryDetails("address")
        )
    }

    static OrderToPay getAggregate(Map map = [:]) {
        return new OrderToPay(
                map.id ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.items ?: [new OrderItem(UUID.randomUUID(), 1)],
                map.amount ?: BigDecimal.ZERO,
                map.deliveryDetails ?: new DeliveryDetails("address"),
                map.isPaid ?: false,
                map.isRefuned ?: false
        )
    }

    static PayOrderCommand getPayOrderCommand(Map map = [:]) {
        return new PayOrderCommand(
                map.orderId ?: UUID.randomUUID()
        )
    }
}
