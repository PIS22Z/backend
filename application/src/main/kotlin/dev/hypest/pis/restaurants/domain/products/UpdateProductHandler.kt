package dev.hypest.pis.restaurants.domain.products

import jakarta.inject.Singleton
import java.math.BigDecimal
import java.util.UUID

interface UpdateProductHandler {
    fun update(command: UpdateProductCommand): UUID
}

@Singleton
class UpdateProductHandlerImpl(
    private val productRepository: ProductRepository
) : UpdateProductHandler {
    override fun update(command: UpdateProductCommand): UUID {
        val product = productRepository.load(command.productId)
            ?: throw ProductNotFoundException(command.productId)
        val newProduct = product.update(
            name = command.name,
            photoUrl = command.photoUrl,
            price = command.price
        )
        productRepository.save(newProduct)
        return product.id
    }
}

data class UpdateProductCommand(
    val productId: UUID,
    val name: String?,
    val photoUrl: String?,
    val price: BigDecimal?
)
