package dev.hypest.pis.orders.domain.restaurants

import java.util.UUID

interface OrdersToRestaurantsPort {

    fun getProduct(productId: UUID): Product?
}
