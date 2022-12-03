package dev.hypest.pis.restaurants.domain.products

import jakarta.inject.Singleton
import java.util.UUID

interface DeleteProductHandler {
    fun delete(productId: UUID)
}

@Singleton
class DeleteProductHandlerImpl(
    private val productRepository: ProductRepository
) : DeleteProductHandler {
    override fun delete(productId: UUID) {
        val product = productRepository.load(productId) ?: throw ProductNotFoundException(productId)
        productRepository.remove(product.id)
    }
}
