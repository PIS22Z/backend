package dev.hypest.pis.payments.infrastructure.db.ordertopay

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.math.BigDecimal
import java.util.UUID

@MappedEntity("order_to_pay")
data class OrderToPayEntity(
    @field:Id
    val id: UUID,
    val userId: UUID,
    val amount: BigDecimal,
    val deliveryDetails: DeliveryDetails,
    val isPaid: Boolean
)
