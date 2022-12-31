package dev.hypest.pis.restaurants.product

import java.math.BigDecimal

data class UpdateProductRequest(
    val name: String,
    val photoUrl: String,
    val price: BigDecimal
)
