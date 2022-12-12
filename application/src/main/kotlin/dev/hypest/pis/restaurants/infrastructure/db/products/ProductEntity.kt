package dev.hypest.pis.restaurants.infrastructure.db.products

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.math.BigDecimal
import java.util.UUID

@MappedEntity("product")
data class ProductEntity(
    @field:Id
    val id: UUID,
    val restaurantId: UUID,
    val name: String,
    val photoUrl: String,
    val price: BigDecimal
)
