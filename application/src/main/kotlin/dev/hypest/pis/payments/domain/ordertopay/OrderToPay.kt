package dev.hypest.pis.payments.domain.ordertopay

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import java.math.BigDecimal
import java.util.UUID

data class OrderToPay(
    val id: UUID,
    val userId: UUID,
    val amount: BigDecimal,
    val deliveryDetails: DeliveryDetails,
    var isPaid: Boolean
) : AggregateRoot() {

    fun pay() {
        isPaid = true
    }

    companion object {
        @JvmStatic
        fun new(
            userId: UUID,
            amount: BigDecimal,
            deliveryDetails: DeliveryDetails
        ): OrderToPay {
            return OrderToPay(
                id = UUID.randomUUID(),
                userId = userId,
                amount = amount,
                deliveryDetails = deliveryDetails,
                isPaid = false
            )
        }
    }
}
