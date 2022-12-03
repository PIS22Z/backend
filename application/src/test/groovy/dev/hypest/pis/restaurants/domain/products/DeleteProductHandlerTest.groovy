package dev.hypest.pis.restaurants.domain.products

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.ProductTestProvider
import jakarta.inject.Inject

class DeleteProductHandlerTest extends BaseTest {

    @Inject
    private DeleteProductHandler deleteProductHandler

    @Inject
    private ProductRepository productRepository

    def "given existing product, when it is deleted, then it should be deleted from db"() {
        given:
        def existingProduct = ProductTestProvider.getAggregate()
        productRepository.add(existingProduct)

        when:
        deleteProductHandler.delete(existingProduct.id)

        then:
        productRepository.load(existingProduct.id) == null
    }
}
