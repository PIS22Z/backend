@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.mapper

import dev.hypest.pis.restaurants.domain.products.CreateProductCommand
import dev.hypest.pis.restaurants.domain.products.UpdateProductCommand
import dev.hypest.pis.restaurants.product.CreateProductRequest
import dev.hypest.pis.restaurants.product.UpdateProductRequest
import java.util.UUID

object ProductMapper {

    fun mapToCreateProductCommand(
        restaurantId: UUID,
        request: CreateProductRequest
    ): CreateProductCommand = CreateProductCommand(
        restaurantId = restaurantId,
        name = request.name,
        price = request.price,
        photoUrl = request.photoUrl,
    )

    fun mapToUpdateProductCommand(
        productId: UUID,
        request: UpdateProductRequest
    ) = UpdateProductCommand(
            productId = productId,
            name = request.name,
            price = request.price,
            photoUrl = request.photoUrl
        )
}
