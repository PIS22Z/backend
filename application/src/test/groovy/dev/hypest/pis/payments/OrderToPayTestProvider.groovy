package dev.hypest.pis.payments

import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails
import dev.hypest.pis.payments.domain.ordertopay.OrderItem
import dev.hypest.pis.payments.domain.ordertopay.OrderToPay
import dev.hypest.pis.payments.domain.ordertopay.PayOrderCommand

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
                map.isPaid ?: false
        )
    }

    static PayOrderCommand getPayOrderCommand(Map map = [:]) {
        return new PayOrderCommand(
                map.orderId ?: UUID.randomUUID()
        )
    }
}
