package dev.hypest.pis.delivery

import dev.hypest.pis.delivery.domain.orderdelivery.AcceptOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.DeliveryDetails
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDelivery

class OrderDeliveryTestProvider {

    static CreateOrderDeliveryCommand getCreateOrderDeliveryCommand() {
        return new CreateOrderDeliveryCommand(UUID.randomUUID(), UUID.randomUUID(), new DeliveryDetails("address"))
    }

    static AcceptOrderDeliveryCommand getAcceptOrderDeliveryCommand(Map map = [:]) {
        return new AcceptOrderDeliveryCommand(
                map.orderDeliveryId ?: UUID.randomUUID(),
                map.courierId ?: UUID.randomUUID()
        )
    }

    static OrderDelivery getAggregate(Map map = [:]) {
        return OrderDelivery.new(UUID.randomUUID(), UUID.randomUUID(), new DeliveryDetails("address"))
    }
}
