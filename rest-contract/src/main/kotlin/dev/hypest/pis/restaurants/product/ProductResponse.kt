package dev.hypest.pis.restaurants.product

import java.math.BigDecimal
import java.util.UUID

data class ProductResponse(
    val id: UUID,
    val name: String,
    val photoUrl: String,
    val price: BigDecimal,
)
