package dev.hypest.pis.restaurants


import dev.hypest.pis.payments.OrderPaidEvent

class OrderPaidEventTestProvider {

    static OrderPaidEvent getOrderPaidEvent(Map map = [:]) {
        new OrderPaidEvent(
                map.orderId ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.items ?: [new OrderPaidEvent.OrderItem(UUID.randomUUID(), 1)],
                map.deliveryDetails ?: new OrderPaidEvent.DeliveryDetails(
                        map.address ?: "Test address"
                )
        )
    }
}
