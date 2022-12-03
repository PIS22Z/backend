package dev.hypest.pis.restaurants.product

import java.math.BigDecimal
import java.util.UUID

class ProductItem(
    val id: UUID,
    val name: String,
    val price: BigDecimal,
    val photoUrl: String,
)
