package dev.hypest.pis.delivery

import java.util.UUID

data class OrderDeliveryOfferResponse(
    val id: UUID,
    val restaurantId: UUID,
    val deliveryDetails: DeliveryDetails
) {
    data class DeliveryDetails(
        val address: String
    )
}
