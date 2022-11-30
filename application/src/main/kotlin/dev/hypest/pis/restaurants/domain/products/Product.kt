package dev.hypest.pis.restaurants.domain.products

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import java.math.BigDecimal
import java.util.UUID

data class Product(
    val id: UUID,
    val restaurantId: UUID,
    val name: String,
    val photoUrl: String,
    val price: BigDecimal
) : AggregateRoot() {
    companion object {

        @JvmStatic
        fun new(restaurantId: UUID, name: String, photoUrl: String, price: BigDecimal): Product {
            return Product(
                id = UUID.randomUUID(),
                restaurantId = restaurantId,
                name = name,
                photoUrl = photoUrl,
                price = price
            )
        }
    }
}
