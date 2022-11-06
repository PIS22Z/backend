package dev.hypest.pis.payments

import dev.hypest.pis.orders.OrderFinalizedEvent

class OrderFinalizedEventTestProvider {

    static OrderFinalizedEvent getOrderFinalizedEvent(Map map = [:]) {
        new OrderFinalizedEvent(
                map.id ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.amount ?: BigDecimal.ZERO
        )
    }
}
