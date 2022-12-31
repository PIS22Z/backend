package dev.hypest.pis.restaurants.infrastructure.db.restaurant

import dev.hypest.pis.restaurants.infrastructure.db.products.ProductEntity
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID

@MappedEntity("restaurants")
data class RestaurantEntity(
    @field:Id
    val id: UUID,
    val name: String,
    val logoUrl: String,
    val description: String,
    val products: List<ProductEntity>,
    val city: String,
    val street: String,
    val number: String,
)
