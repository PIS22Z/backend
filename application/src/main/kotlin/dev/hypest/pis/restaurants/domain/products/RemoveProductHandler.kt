package dev.hypest.pis.restaurants.domain.products

import jakarta.inject.Singleton
import java.util.UUID

interface RemoveProductHandler {
    fun remove(productId: UUID)
}

@Singleton
class RemoveProductHandlerImpl(
    private val productRepository: ProductRepository
) : RemoveProductHandler {
    override fun remove(productId: UUID) {
        val product = productRepository.load(productId) ?: throw ProductNotFoundException(productId)
        productRepository.remove(product.id)
    }
}
