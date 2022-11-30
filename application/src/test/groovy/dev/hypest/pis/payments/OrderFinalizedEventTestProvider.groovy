package dev.hypest.pis.payments

import dev.hypest.pis.orders.OrderFinalizedEvent

class OrderFinalizedEventTestProvider {

    static OrderFinalizedEvent getOrderFinalizedEvent(Map map = [:]) {
        new OrderFinalizedEvent(
                map.orderId ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.items ?: [new OrderFinalizedEvent.OrderItem(UUID.randomUUID(), 1)],
                map.amount ?: BigDecimal.ZERO,
                map.deliveryDetails ?: new OrderFinalizedEvent.DeliveryDetails("address")
        )
    }
}
