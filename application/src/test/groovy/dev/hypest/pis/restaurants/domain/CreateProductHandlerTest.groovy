package dev.hypest.pis.restaurants.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.ProductTestProvider
import dev.hypest.pis.restaurants.domain.products.CreateProductHandler
import dev.hypest.pis.restaurants.domain.products.ProductRepository
import jakarta.inject.Inject

class CreateProductHandlerTest extends BaseTest {

    @Inject
    private CreateProductHandler handler

    @Inject
    private ProductRepository productRepository

    def "given nothing, when valid product is created, then it should be saved in db"() {
        given:
        def command = ProductTestProvider.getCreateProductCommand()

        when:
        def id = handler.create(command)

        then:
        def savedProduct = productRepository.load(id)
        savedProduct != null
        savedProduct.id == id
        savedProduct.name == command.name
        savedProduct.price == command.price
        savedProduct.restaurantId == command.restaurantId
    }
}
