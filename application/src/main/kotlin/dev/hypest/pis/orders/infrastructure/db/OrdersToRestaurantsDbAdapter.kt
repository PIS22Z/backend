package dev.hypest.pis.orders.infrastructure.db

import dev.hypest.pis.common.unwrap
import dev.hypest.pis.orders.domain.restaurants.OrdersToRestaurantsPort
import dev.hypest.pis.orders.domain.restaurants.Product
import dev.hypest.pis.restaurants.infrastructure.db.product.MicronautDataProductRepository
import dev.hypest.pis.restaurants.infrastructure.db.product.ProductEntity
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class OrdersToRestaurantsDbAdapter(
    private val repository: MicronautDataProductRepository
) : OrdersToRestaurantsPort {

    override fun getProduct(productId: UUID): Product? {
        return repository.findById(productId).unwrap()?.let { mapToProduct(it) }
    }

    private fun mapToProduct(productEntity: ProductEntity): Product {
        return Product(
            id = productEntity.id,
            price = productEntity.price
        )
    }
}
