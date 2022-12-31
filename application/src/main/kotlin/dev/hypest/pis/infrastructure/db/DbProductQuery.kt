package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.common.unwrap
import dev.hypest.pis.restaurants.adapter.`in`.query.ProductQuery
import dev.hypest.pis.restaurants.domain.products.ProductNotFoundInRestaurant
import dev.hypest.pis.restaurants.infrastructure.db.products.MicronautDataProductRepository
import dev.hypest.pis.restaurants.product.ProductResponse
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbProductQuery(
    private val productRepository: MicronautDataProductRepository,
) : ProductQuery {
    override fun getProductsFromRestaurant(restaurantId: UUID): List<ProductResponse> =
        productRepository.findByRestaurantId(restaurantId).map { product ->
            ProductResponse(
                id = product.id,
                name = product.name,
                photoUrl = product.photoUrl,
                price = product.price
            )
        }

    override fun getProduct(restaurantId: UUID, productId: UUID): ProductResponse {
        val product = productRepository.findByRestaurantIdAndId(restaurantId, productId).unwrap()
            ?: throw ProductNotFoundInRestaurant(restaurantId, productId)
        return ProductResponse(
            id = product.id,
            name = product.name,
            photoUrl = product.photoUrl,
            price = product.price
        )
    }
}
