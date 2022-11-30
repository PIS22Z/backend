package dev.hypest.pis.payments

import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails
import dev.hypest.pis.payments.domain.ordertopay.OrderItem

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
}
