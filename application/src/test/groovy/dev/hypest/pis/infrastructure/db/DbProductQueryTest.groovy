package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.adapter.in.query.ProductQuery
import dev.hypest.pis.restaurants.infrastructure.db.products.MicronautDataProductRepository
import dev.hypest.pis.restaurants.infrastructure.db.products.ProductEntity
import jakarta.inject.Inject

class DbProductQueryTest extends BaseTest {

    @Inject
    private ProductQuery productQuery

    @Inject
    private MicronautDataProductRepository productRepository


    def "given existing product, when product is queried, then it should return product"() {
        given:
        def existingProduct = productRepository.save(getProductEntity())

        when:
        def product = productQuery.getProduct(existingProduct.restaurantId, existingProduct.id)

        then:
        product.id == existingProduct.id
        product.name == existingProduct.name
        product.photoUrl == existingProduct.photoUrl
        product.price == existingProduct.price
    }

    def "given existing product, when products are queried from the restaurant, then it should return product"() {
        given:
        def restaurantId = UUID.randomUUID()
        def existingProducts = productRepository.saveAll(
                ([{ getProductEntity(restaurantId: restaurantId) }] * 5)*.call()
        )
        productRepository.save(getProductEntity())

        when:
        def products = productQuery.getProductsFromRestaurant(restaurantId)

        then:
        products.size() == 5
        existingProducts.eachWithIndex { it, index ->
            products[index].id == it.id
            products[index].name == it.name
            products[index].photoUrl == it.photoUrl
            products[index].price == it.price
        }
    }


    private static ProductEntity getProductEntity(Map map = [:]) {
        return new ProductEntity(
                UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.name ?: "name",
                map.photoUrl ?: "photoUrl",
                map.price ?: BigDecimal.ZERO,
        )
    }
}
