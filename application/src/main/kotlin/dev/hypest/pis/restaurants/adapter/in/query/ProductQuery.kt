@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.query

import dev.hypest.pis.restaurants.product.ProductResponse
import java.util.UUID

interface ProductQuery {
    fun getProductsFromRestaurant(restaurantId: UUID): List<ProductResponse>
    fun getProduct(restaurantId: UUID, productId: UUID): ProductResponse
}
