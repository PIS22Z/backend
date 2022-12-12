package dev.hypest.pis.restaurants.domain

import jakarta.inject.Singleton
import java.math.BigDecimal
import java.util.UUID


interface CreateProductHandler {
    fun create(command: CreateProductCommand): UUID
}

@Singleton
class CreateProductHandlerImpl(
    private val productRepository: ProductRepository
) : CreateProductHandler {
    override fun create(command: CreateProductCommand): UUID {
        val product = Product.new(
            restaurantId = command.restaurantId,
            name = command.name,
            photoUrl = command.photoUrl,
            price = command.price
        )
        productRepository.add(product)
        return product.id
    }
}

data class CreateProductCommand(
    val userId: UUID,
    val restaurantId: UUID,
    val name: String,
    val photoUrl: String,
    val price: BigDecimal
)
