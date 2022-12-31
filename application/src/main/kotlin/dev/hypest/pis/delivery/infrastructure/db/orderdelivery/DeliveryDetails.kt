package dev.hypest.pis.delivery.infrastructure.db.orderdelivery

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class DeliveryDetails(
    val address: String
)
