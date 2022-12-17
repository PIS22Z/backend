package dev.hypest.pis.delivery.domain.orderdelivery

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import java.util.UUID

data class OrderDelivery(
    val id: UUID,
    val restaurantId: UUID,
    val deliveryDetails: DeliveryDetails,
    var assignedCourierId: UUID? = null,
) : AggregateRoot() {

    companion object {
        @JvmStatic
        fun new(
            id: UUID,
            restaurantId: UUID,
            deliveryDetails: DeliveryDetails
        ): OrderDelivery {
            return OrderDelivery(
                id = id,
                restaurantId = restaurantId,
                deliveryDetails = deliveryDetails
            )
        }
    }

    fun accept(courierId: UUID) {
        assignedCourierId = courierId
    }
}
