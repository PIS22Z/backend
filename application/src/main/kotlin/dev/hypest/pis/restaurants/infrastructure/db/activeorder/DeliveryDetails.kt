package dev.hypest.pis.restaurants.infrastructure.db.activeorder

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class DeliveryDetails(
    val address: String
)
