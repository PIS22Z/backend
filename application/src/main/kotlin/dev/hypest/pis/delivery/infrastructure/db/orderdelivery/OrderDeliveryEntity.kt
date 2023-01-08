package dev.hypest.pis.delivery.infrastructure.db.orderdelivery

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID

@MappedEntity("order_delivery")
data class OrderDeliveryEntity(
    @field:Id
    val id: UUID,
    val restaurantId: UUID,
    val deliveryDetails: DeliveryDetails,
    val assignedCourierId: UUID?,
    val isBeingDelivered: Boolean,
    val isDelivered: Boolean
)
