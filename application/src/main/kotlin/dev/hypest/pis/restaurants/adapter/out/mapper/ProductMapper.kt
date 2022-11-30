package dev.hypest.pis.restaurants.adapter.out.mapper

import dev.hypest.pis.restaurants.domain.products.Product
import dev.hypest.pis.restaurants.infrastructure.db.products.ProductEntity

object ProductMapper {

    fun mapToProductEntity(product: Product): ProductEntity = ProductEntity(
        id = product.id,
        restaurantId = product.restaurantId,
        name = product.name,
        photoUrl = product.photoUrl,
        price = product.price
    )

    fun mapToProduct(productEntity: ProductEntity): Product = Product(
        id = productEntity.id,
        restaurantId = productEntity.restaurantId,
        name = productEntity.name,
        photoUrl = productEntity.photoUrl,
        price = productEntity.price
    )
}

