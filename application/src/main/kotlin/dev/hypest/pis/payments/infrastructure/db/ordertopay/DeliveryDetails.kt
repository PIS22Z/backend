package dev.hypest.pis.payments.infrastructure.db.ordertopay

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class DeliveryDetails(
    val address: String
)
