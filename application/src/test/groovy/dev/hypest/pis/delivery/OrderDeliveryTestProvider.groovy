package dev.hypest.pis.delivery

import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.DeliveryDetails
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDelivery

class OrderDeliveryTestProvider {

    static CreateOrderDeliveryCommand getCreateOrderDeliveryCommand() {
        return new CreateOrderDeliveryCommand(UUID.randomUUID(), UUID.randomUUID(), new DeliveryDetails("address"))
    }

    static OrderDelivery getAggregate(Map map = [:]) {
        return OrderDelivery.new(UUID.randomUUID(), UUID.randomUUID(), new DeliveryDetails("address"))
    }
}
