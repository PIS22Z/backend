package dev.hypest.pis.restaurants

import dev.hypest.pis.restaurants.domain.activeorder.ActiveOrder
import dev.hypest.pis.restaurants.domain.activeorder.DeliveryDetails

class ActiveOrderTestProvider {

    static ActiveOrder getAggregate(Map map = [:]) {
        return new ActiveOrder(
                map.id ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.userId ?: UUID.randomUUID(),
                map.items ?: [],
                map.deliveryDetails ?: new DeliveryDetails("address"),
                map.isAccepted,
                map.isReadyToDeliver ?: false
        )
    }
}
