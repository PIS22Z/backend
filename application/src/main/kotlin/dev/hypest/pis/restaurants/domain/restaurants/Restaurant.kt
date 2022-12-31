package dev.hypest.pis.restaurants.domain.restaurants

import dev.hypest.pis.common.eventaggregator.AggregateRoot
import dev.hypest.pis.restaurants.domain.products.Product
import java.util.UUID

data class Restaurant(
    val id: UUID,
    val name: String,
    val logoUrl: String,
    val description: String,
    val products: List<Product>,
    val street: String,
    val number: String,
    val city: String,
) : AggregateRoot() {
    companion object {

        @Suppress("LongParameterList")
        @JvmStatic
        fun new(
            name: String,
            logoUrl: String,
            description: String,
            products: List<Product>,
            street: String,
            number: String,
            city: String
        ): Restaurant = Restaurant(
            id = UUID.randomUUID(),
            name = name,
            logoUrl = logoUrl,
            description = description,
            products = products,
            street = street,
            number = number,
            city = city
        )
    }

    @Suppress("LongParameterList")
    fun update(
        name: String?,
        logoUrl: String?,
        description: String?,
        city: String?,
        street: String?,
        number: String?,
    ): Restaurant = Restaurant(
        id = id,
        name = name ?: this.name,
        logoUrl = logoUrl ?: this.logoUrl,
        description = description ?: this.description,
        products = products,
        street = street ?: this.street,
        number = number ?: this.number,
        city = city ?: this.city
    )
}
