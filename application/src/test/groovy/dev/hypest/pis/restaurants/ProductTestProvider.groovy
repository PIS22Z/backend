package dev.hypest.pis.restaurants

import dev.hypest.pis.restaurants.domain.CreateProductCommand

class ProductTestProvider {

    static CreateProductCommand getCreateProductCommand() {
        return new CreateProductCommand(UUID.randomUUID(), UUID.randomUUID(), "name", "url", 1.0)
    }
}
