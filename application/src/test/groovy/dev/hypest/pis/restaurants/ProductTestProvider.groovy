package dev.hypest.pis.restaurants

import dev.hypest.pis.restaurants.domain.products.CreateProductCommand
import dev.hypest.pis.restaurants.domain.products.Product
import dev.hypest.pis.restaurants.domain.products.UpdateProductCommand

class ProductTestProvider {

    static CreateProductCommand getCreateProductCommand(Map map = [:]) {
        return new CreateProductCommand(
                UUID.randomUUID(),
                "name",
                "url",
                1.0
        )
    }

    static UpdateProductCommand getUpdateProductCommand(map = [:]) {
        return new UpdateProductCommand(
                map.id ?: UUID.randomUUID(),
                map.name ?: "name",
                map.imageUrl ?: "url",
                map.price ?: BigDecimal.ZERO
        )
    }

    static Product getAggregate(Map map = [:]) {
        return new Product(
                map.id as UUID ?: UUID.randomUUID(),
                map.restaurantId as UUID ?: UUID.randomUUID(),
                map.name as String ?: "name",
                map.imageUrl as String ?: "url",
                map.price as BigDecimal ?: BigDecimal.ZERO
        )
    }

}
