package dev.hypest.pis.restaurants.domain.products

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.ProductTestProvider
import dev.hypest.pis.restaurants.domain.restaurants.RestaurantRepository
import jakarta.inject.Inject

class UpdateProductHandlerTest extends BaseTest {

    @Inject
    private UpdateProductHandler handler

    @Inject
    private ProductRepository productRepository

    @Inject
    private RestaurantRepository restaurantRepository

    def "given existing product, when it is updated, then it should be updated in db"() {
        given:
        def existingProduct = ProductTestProvider.getAggregate(
            name: "existing product",
            price: BigDecimal.ZERO,
        )
        productRepository.add(existingProduct)

        def command = ProductTestProvider.getUpdateProductCommand(
            id: existingProduct.id,
            name: "updated product",
            price: BigDecimal.ONE,
            restaurantId: "updated restaurantId"
        )

        when:
        def id = handler.update(command)

        then:
        def savedProduct = productRepository.load(id)
        savedProduct != null
        savedProduct.id == id
        savedProduct.name == command.name
        savedProduct.price == command.price
    }
}
