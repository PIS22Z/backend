package dev.hypest.pis.payments

import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails

class OrderToPayTestProvider {

    static CreateOrderToPayCommand getCreateOrderToPayCommand(Map map = [:]) {
        return new CreateOrderToPayCommand(
                map.orderId ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.amount ?: BigDecimal.ZERO,
                map.deliveryDetails ?: new DeliveryDetails("address")
        )
    }
}
