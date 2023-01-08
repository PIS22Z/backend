package dev.hypest.pis.delivery

import java.math.BigDecimal
import java.util.UUID

data class OrderDeliveryOfferResponse(
    val id: UUID,
    val restaurantId: UUID,
    val restaurantName: String?,
    val restaurantAddress: String?,
    val deliveryRate: BigDecimal,
    val deliveryDetails: DeliveryDetails
) {
    data class DeliveryDetails(
        val address: String
    )
}
