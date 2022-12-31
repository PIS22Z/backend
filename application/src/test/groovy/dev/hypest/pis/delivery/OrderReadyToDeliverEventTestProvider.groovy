package dev.hypest.pis.delivery


import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent

class OrderReadyToDeliverEventTestProvider {

    static OrderReadyToDeliverEvent getOrderReadyToDeliverEvent(Map map = [:]) {
        new OrderReadyToDeliverEvent(
                map.orderId ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.deliveryDetails ?: new OrderReadyToDeliverEvent.DeliveryDetails("address")
        )
    }
}
