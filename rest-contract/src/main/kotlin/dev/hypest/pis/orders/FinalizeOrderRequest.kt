package dev.hypest.pis.orders

import java.util.UUID

data class FinalizeOrderRequest(
    val userId: UUID,
    val deliveryDetails: DeliveryDetails
) {
    data class DeliveryDetails(
        val address: String
    )
}
