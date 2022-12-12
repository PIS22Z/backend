package dev.hypest.pis.orders.domain.restaurants

import java.math.BigDecimal
import java.util.UUID

data class Product(
    val id: UUID,
    val price: BigDecimal
)
