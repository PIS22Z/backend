package dev.hypest.pis.restaurants.domain.products

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.ProductTestProvider
import jakarta.inject.Inject

class RemoveProductHandlerTest extends BaseTest {

    @Inject
    private RemoveProductHandler removeProductHandler

    @Inject
    private ProductRepository productRepository

    def "given existing product, when it is removed, then it should be removed from db"() {
        given:
        def existingProduct = ProductTestProvider.getAggregate()
        productRepository.add(existingProduct)

        when:
        removeProductHandler.remove(existingProduct.id)

        then:
        productRepository.load(existingProduct.id) == null
    }
}
