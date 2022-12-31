package dev.hypest.pis.restaurants.product

import java.math.BigDecimal

data class CreateProductRequest(
    val name: String,
    val photoUrl: String,
    val price: BigDecimal
)
